package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.model.UserInformEntity;
import com.mao.model.UsersGroupEntity;
import com.mao.pojo.VUsersGroupPojo;
import com.mao.pojo.VUsersIcons;
import com.mao.service.FriendService;
import com.mao.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private InfoService infoService;

    /**
     * 用于处理用户同意好友请求后
     * 对服务器的请求
     * @param usersGroupEntity 用户分组实体类
     * @param userInformEntity 用户通知实体类
     * @param groupName 当前用户想要放入的好友分组
     * @param remark 当前用户给好友的备注
     * @param fUserId 发送好友请求的用户id
     */
    @RequestMapping(value = "/addFriend")
    @ResponseBody
    public String addFriend(HttpSession session,
                            @Autowired UsersGroupEntity usersGroupEntity,
                            @Autowired UserInformEntity userInformEntity,
                            @RequestParam(required = false) String groupName,
                            @RequestParam(required = false) String remark,
                            @RequestParam(required = false) String fUserId) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        // 获得当前用户的用户id
        int cUserIdNum = userEntity.getUserid();

        // 若请求字符串为空则返回提示字符串
        if (groupName.trim().isEmpty() || remark.trim().isEmpty() || fUserId.trim().isEmpty())
            return JSON.toJSONString("emptyInput");

        int fUserIdNum = Integer.parseInt(fUserId);

        // 应为当前用户为被加为好友的用户
        // 所以对于数据库表中的friendId字段
        usersGroupEntity.setFriendId(cUserIdNum);
        // 设置发送好友请求的用户ID
        usersGroupEntity.setCuserId(fUserIdNum);
        // 设置给发送好友请求的用户的好友分组
        // 由于是被加为好友的用户所以对应数据库表中friendGroup字段
        usersGroupEntity.setFriendGroup(groupName);
        // 设置给发送好友请求的用户好友备注
        // 由于是被加为好友的用户所以对应数据库表中friendRemark字段
        usersGroupEntity.setFriendRemark(remark);
        /*
         *  设定两用户之间的关系，由于发送好友请求的用户在发送信息后已被加入，好友表中
         *  并且设置relationship字段为waitingProcess表示两者的关系正在等待结果
         *  在被动用户同意加为好友后,将relationship字段设为friend，表示双方互为好友
                */
        usersGroupEntity.setRelationShip("friend");

        userInformEntity.setFuserId(fUserIdNum);
        userInformEntity.setTuserId(cUserIdNum);
        userInformEntity.setActive("yes");
        // 需要将收到的消息变为以读状态
        Boolean infoResult = infoService.updateActive(userInformEntity);
        // 更改两用户之间的关系，使其成为好友
        Boolean friendResult = friendService.changeUserRelationShip(usersGroupEntity);
        // 若请求数据库成功，则返回成功的提示字符串(JSON格式),否则返回提示失败的字符串(JSON格式)
        return infoResult && friendResult ? JSON.toJSONString("success") : JSON.toJSONString("fail");

    }

    /**
     * 用于处理列出用户好友的请求
     */
    @RequestMapping(value = "/listFriend")
    @ResponseBody
    public String listFriend(HttpSession session) {

        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        // 获得当前用户的id
        Integer cuserId = userEntity.getUserid();

        List<VUsersGroupPojo> friendPojoList = friendService.printGroupAndFriendUnderGroup(cuserId);

        // 如果当前用户没有好友则给出提示信息
        return friendPojoList.isEmpty() ? JSON.toJSONString("noFriend") : JSON.toJSONString(friendPojoList);
    }

    /**
     * 用于处理用户查看好友信息的请求
     */
    @RequestMapping(value = "/viewFriend")
    @ResponseBody
    public String viewFriend(HttpServletRequest request) {
        String tUserIdStr = request.getParameter("tUserId");
        // 判断客户端是否出现bug导致无法获取好友的id
        if (tUserIdStr == null ||tUserIdStr.isEmpty())
            return JSON.toJSONString("emptyError");
        // 将字符串id转化为8位整型id
        Integer tUserId = Integer.parseInt(tUserIdStr);

        VUsersIcons VUsersIcons = friendService.viewFriendById(tUserId);
        // 若出现异常bug，则返回错误的提示字符串

        return VUsersIcons == null ? JSON.toJSONString("error") : JSON.toJSONString(VUsersIcons);
    }
}
