package com.mao.controller;


import com.alibaba.fastjson.JSON;
import com.mao.model.GroupEntity;
import com.mao.model.UserEntity;
import com.mao.pojo.VUsersGroupPojo;
import com.mao.service.FriendService;
import com.mao.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    FriendService friendService;

    /**
     * 用于用户添加分组
     */
    @RequestMapping(value = "/addGroup")
    @ResponseBody
    public String addGroup(GroupEntity groupEntity, HttpServletRequest request, HttpSession session) {

        // 获得session中的user对象
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        // 获得用户id
        int userId = userEntity.getUserid();

        // 获得即将被添加的分组的名字
        String groupName = request.getParameter("groupName");

        groupEntity.setUserid(userId);
        groupEntity.setGroupname(groupName);

        // 判断当前用户是否已经拥有同名分组
        if (groupService.whetherHasThisGroup(groupEntity)) {
            return JSON.toJSONString("alreadyHasThisGroup");
        } else {
            // 判断是否成功添加分组
            Boolean result = groupService.addGroup(groupEntity);

            if (result) {
                //在用户添加完分组后重新设置session中的group值
                session.removeAttribute("userGroup");
                session.setAttribute("userGroup", groupService.getAllGroups(userEntity.getUserid()));
                return JSON.toJSONString("successAddGroup");
            } else
                return JSON.toJSONString("errorAddGroup");
        }
    }

    /**
     * 用于用户删除分组
     * 当用户删除分组后，该分组下的所有用户移到默认分组
     * 默认分组不可删除
     *
     * @param groupName 当前用户要删除的分组的名字
     */
    @RequestMapping(value = "/delGroup")
    @ResponseBody
    public String delGroup(@RequestParam(required = false) String groupName,
                           HttpSession session) {
        // 如果要删除的分组的名称为空，则返回提示字符串
        if (groupName == null || groupName.isEmpty()) {
            return JSON.toJSONString("emptyGroupName");
        }
        // 从 session 中获取 用户id
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        int userId = userEntity.getUserid();

        // 实例化用户分组实体类对象
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setUserid(userId);
        groupEntity.setGroupname(groupName);

        // 现将当前分组下的所有用户移动到默认分组中
        Boolean moveResult = friendService.changeUsersGroupToDefault(groupEntity);
        // 如果没有成功移动，则返回提示字符串
        if (!moveResult) {
            return JSON.toJSONString("errorMoving");
        }
        // 如果移动成功，则删除当期用户的指定分组
        Boolean delResult = groupService.delGroup(groupEntity);

        if(!delResult){
            return JSON.toJSONString("errorDeleting");
        }
        return JSON.toJSONString("success");

    }

    /**
     * 用于更新分组的名称
     * 更改分组名称之前需要先更改好友所在的分组名称
     * */
    @RequestMapping(value = "/upGroup")
    @ResponseBody
    public String updateGroup(){
        return null;
    }

    /**
     * 用于获取用户的所有分组
     */
    @RequestMapping(value = "/printGroup")
    @ResponseBody
    @SuppressWarnings("all")
    public String printGroup(HttpSession session) {
        // 从session中获取用户分组
        List<String> groupList = null;
        groupList = (List<String>) session.getAttribute("userGroup");
        // 如果session中没有分组
        if (groupList == null) {
            // 获得session中的用户对象
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            // 获取当前用户的所有分组的集合
            groupList = groupService.getAllGroups(userEntity.getUserid());

            if (groupList.isEmpty()) {
                return JSON.toJSONString("noGroup");
            } else {
                session.setAttribute("userGroup", groupList);
                return JSON.toJSONString(groupList);
            }
            // 若session中有分组则直接返回
        } else {
            return JSON.toJSONString(groupList);
        }

    }

}
