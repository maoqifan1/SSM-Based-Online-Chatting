package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.model.UserInformEntity;
import com.mao.model.UsersGroupEntity;
import com.mao.pojo.userInformPojo;
import com.mao.service.FriendService;
import com.mao.service.InfoService;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/info"})
public class InfoController {
    /**
     * 处理通知信息的控制器类
     */
    @Autowired
    private InfoService infoService;
    @Autowired
    private FriendService friendService;

    // 初始化页面并返回view
    @RequestMapping({"/page"})
    public String initPage() {
        return "info";
    }


    /**
     * @param userInformEntity 是存储用户通知信息的java对象
     * @param usersGroupEntity 是存储好友间信息的java对象
     * */
    @RequestMapping(value = "/insertInfo")
    @ResponseBody
    public String sendInfo(HttpServletRequest request,
                           HttpSession session,
                           @Autowired UserInformEntity userInformEntity,
                           @Autowired UsersGroupEntity usersGroupEntity) {
        // 获得session中的user对象
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        // 获得当前用户的id
        int fUserId = userEntity.getUserid();
        int tUserId = 0;
        // 获得将要被加为好友的user的id
        String tUserIdStr = request.getParameter("tUserId");
        if (tUserIdStr == null || tUserIdStr.trim().isEmpty()) {
            JSON.toJSONString("error");
        } else {
            tUserId = Integer.parseInt(tUserIdStr);
        }
        // 获得 提示，分组和备注信息
        String info = request.getParameter("info");
        String groupName = request.getParameter("groupName");
        String remark = request.getParameter("remark");
        // 若三者信息都不为空
        if (!info.trim().isEmpty() && !groupName.trim().isEmpty() && !remark.trim().isEmpty()) {
            // 初始化对象
            // 设定当前用户的id
            usersGroupEntity.setCuserId(fUserId);
            // 设定将要被加为好友的id
            usersGroupEntity.setFriendId(tUserId);
            // 设定关系为waitingProcess时则表示两者并不是好友状态
            usersGroupEntity.setRelationShip("waitingProcess");
            // 设定 对好友的备注
            usersGroupEntity.setUserRemark(remark);
            // 设定对好友的分组
            usersGroupEntity.setUserGroup(groupName);

            Boolean friendResult = this.friendService.AddUser(usersGroupEntity);
            userInformEntity.setFuserId(fUserId);
            userInformEntity.setTuserId(tUserId);
            userInformEntity.setInfo(info);
            Boolean infoResult = this.infoService.sendInfo(userInformEntity);
            // 如果信息和好友表都插入成功则返回成功的消息
            // 否则返回失败消息
            return infoResult && friendResult ? JSON.toJSONString("successSending") : JSON.toJSONString("errorSending");
        } else {
            return JSON.toJSONString("emptyInput");
        }
    }

    @RequestMapping(value = "/showInfo")
    @ResponseBody
    public String showInfo(HttpSession session) {
        // 从session中获得当前用户的所有通知信息
        @SuppressWarnings("All")
        List<userInformPojo> informPojoList = (List) session.getAttribute("informPojoList");
        if (informPojoList != null) {
            return JSON.toJSONString(informPojoList);
        } else {
            // 如果session中的对象为空则从数据库中取出数据
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            informPojoList = this.infoService.showInfo(userEntity.getUserid());
            session.setAttribute("informPojoList", informPojoList);
            return JSON.toJSONString(informPojoList);
        }
    }
}
