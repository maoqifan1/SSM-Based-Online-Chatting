package com.mao.controller;


import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.pojo.searchPojo;
import com.mao.pojo.VUsersIcons;
import com.mao.service.GroupService;
import com.mao.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private GroupService groupService;

    /**
     * 初始化搜索页面的数据绑定
     * */
    @RequestMapping("/searchInput")
    public String inputSearch(@ModelAttribute("searchPojo") searchPojo searchPojo, Model model,
                               HttpSession session){
       __initData__(model);
       // 将用户的分组信息放入session中
        if (session.getAttribute("userGroup")== null) {
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            session.setAttribute("userGroup", groupService.getAllGroups(userEntity.getUserid()));
        }
       return "search";
    }


    /**
     * 根据用户所选择的索引查找用户
     * */
    @RequestMapping(value = "/searchUserByCustomMethod")
    @ResponseBody
    public String searchUserByCustomMethod(@ModelAttribute("searchPojo") searchPojo searchPojo){
        if(validateInput(searchPojo).equals("emptyInput")){
            return JSON.toJSONString("emptyInput");
        }else{
            //  根据 ID 查找用户
            if(searchPojo.getSearchItem().equals("ID")){
                List<VUsersIcons> userList = searchService.searchUserByID(Integer.parseInt(searchPojo.getSearchInput()));
                return userList.isEmpty()?JSON.toJSONString("notFound"):JSON.toJSONString(userList);
                // 根据用户名查找用户
            }else if(searchPojo.getSearchItem().equals("Name")){
                List<VUsersIcons> userList = searchService.searchUserByUserName(searchPojo.getSearchInput());
                return userList.isEmpty()?JSON.toJSONString("notFound"):JSON.toJSONString(userList);
                // 根据邮箱查找用户
            }else if(searchPojo.getSearchItem().equals("Email")){
                List<VUsersIcons> userList = searchService.searchUserByEmail(searchPojo.getSearchInput());
                return userList.isEmpty()?JSON.toJSONString("notFound"):JSON.toJSONString(userList);
                // 根据电话查找用户
            }else{
                List<VUsersIcons> userList = searchService.searchUserByPhone(searchPojo.getSearchInput());
                return userList.isEmpty()?JSON.toJSONString("notFound"):JSON.toJSONString(userList);
            }
        }
    }

    /**
     * 私有方法，初始化model中的数据
     * */
    private void __initData__(Model model){
        String []searchItems = new String[]{"ID","Name","Email","Phone"};
        model.addAttribute("searchItems",searchItems);
    }


    /**
     * 私有方法 ，验证用户输入
     * */
    private String validateInput(@ModelAttribute("searchPojo") searchPojo searchPojo){
        if(searchPojo.getSearchInput().trim().isEmpty()){
            return "emptyInput";
        }else{
            return "Input";
        }
    }

}
