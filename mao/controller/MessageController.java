package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.model.UserMessageEntity;
import com.mao.pojo.VUserUnreadMessageCounts;
import com.mao.pojo.VUsersMessagesPojo;
import com.mao.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageController {
    /**
     * 用于处于处理用户聊天请求的控制器类
     */

    @Autowired
    MessageService messageService;

    /**
     * 返回当前用户与指定用户的聊天记录到客户端
     *
     * @param tUserId 当前用户聊天对象的用户id
     * @param session 会话对象
     */
    @RequestMapping("/getMessage")
    @ResponseBody
    public String getMessage(@RequestParam(required = false) String tUserId,
                             HttpSession session) {

        // 获得用户id
        int cUserIdNum = getUserId(session);

        // 若对方id为空,则返回错误提示
        if (tUserId == null) {
            return JSON.toJSONString("emptyError");
        }
        // 获得对方用户的id
        int tUserIdNum = Integer.parseInt(tUserId);

        // 将id的值映射到 map 中
        Map<String, Integer> userMap = new HashMap<>();
        userMap.put("messageFuserId", cUserIdNum);
        userMap.put("messageTuserId", tUserIdNum);

        // 通过service查询返回数据
        List<VUsersMessagesPojo> messagePojoList = messageService.getMessage(userMap);

        // 如果没有聊天记录，则返回提示
        if (messagePojoList == null || messagePojoList.isEmpty()) {
            return JSON.toJSONString("emptyMessage");
        } else {
            return JSON.toJSONString(messagePojoList);
        }

    }

    /**
     * 用于接收view提交的数据的请求并将数据放入数据库
     *
     * @param tUserId 聊天对象的id
     * @param message 聊天的具体内容
     * @return "emptyError" 表示没有正确的获取到值而返回的提示字符串
     * "error" 表示数据库操作出错
     * "success" 表示所有操作均成功
     */
    @RequestMapping("/setMessage")
    @ResponseBody
    public String setMessage(@RequestParam(required = false) String tUserId,
                             @RequestParam(required = false) String message,
                             HttpSession session) {

        int fUserIdNum = getUserId(session);

        // 若对方id为空或者聊天内容为空字符串或者是空值，则返回错误提示
        if (tUserId == null || message == null || message.trim().isEmpty()) {
            return JSON.toJSONString("emptyError");
        }
        // 获得对方用户的id
        int tUserIdNum = Integer.parseInt(tUserId);

        //实例化UserMessageEntity
        UserMessageEntity userMessageEntity = new UserMessageEntity();
        // 设置发送信息的人的id
        userMessageEntity.setMessageFuserId(fUserIdNum);
        // 设置接收信息的人的id
        userMessageEntity.setMessageTuserId(tUserIdNum);
        // 设置聊天的内容
        userMessageEntity.setMessageContent(message);

        Boolean result = messageService.insertMessage(userMessageEntity);

        return !result ? JSON.toJSONString("error") : JSON.toJSONString("success");
    }

    /**
     * 用于返回给用户所有未读信息的数量
     * 不指定用户，用于在侧边导航栏提示用户
     */
    @RequestMapping("/gums")
    @ResponseBody
    public String getAllUnReadMessageAmounts(HttpSession session) {
        // 获取当前用户的id
        int cUserIdNum = getUserId(session);
        // 获得所有未读消息的数量
        int totalUnreadMessageAmount = messageService.getUnreadMessageAmounts(cUserIdNum);
        // 返回数值
        return JSON.toJSONString(totalUnreadMessageAmount);
    }

    /**
     * 用于返回与指定用户聊天的未读信息的数量
     *
     * @param session 用于获取当前用户的id
     *                <p>
     *                显示在 与用户聊天的会话列表的子项上显示
     */
    @RequestMapping("/guma")
    @ResponseBody
    public String getSpecifiedUnReadMessageAmounts(HttpSession session) {

        // 获得当前用户的id
        int cUserIdNum = getUserId(session);

        // 获得与指定用户的未读消息的数量
        List<VUserUnreadMessageCounts> totalSpecifiedUnreadMessageAmount = messageService.getSpecifiedUnreadMessageAmounts(cUserIdNum);

        return JSON.toJSONString(totalSpecifiedUnreadMessageAmount);

    }

    /**
     * 用于用户在阅读过消息后更新消息的状态
     *
     * @param fUserId 为发送信息的用户的id，应为收到信息通知的人总为当前用户
     */
    @RequestMapping("/upms")
    @ResponseBody
    public String updateMessageStatus(HttpSession session,
                                      @RequestParam(required = false) String fUserId) {
        if (fUserId == null) {
            return JSON.toJSONString("emptyError");
        }
        // 获取当前用户的用户id
        int cUserIdNum = getUserId(session);
        int fUserIdNum = Integer.parseInt(fUserId);

        Map<String, Integer> userMap = new HashMap<>();
        userMap.put("messageFuserId", fUserIdNum);
        userMap.put("messageTuserId", cUserIdNum);

        // 更新会话的状态（已读）
        Boolean uResult = messageService.updateMessageStatusToRead(userMap);
        // 返回提示字符串
        return uResult ? JSON.toJSONString("success") : JSON.toJSONString("error");
    }


    @SuppressWarnings("all")
    private int getUserId(HttpSession session) {
        // 获取存储用户信息的session
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        // 获取当前用户的id
        int cUserId = userEntity.getUserid();

        return cUserId;
    }
}
