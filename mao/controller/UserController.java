package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.calc.RandomNum;
import com.mao.model.GroupEntity;
import com.mao.model.UserEntity;
import com.mao.pojo.userPojo;
import com.mao.service.GroupService;
import com.mao.service.UserIconService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mao.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/user")
public class UserController {
    /*  获得一个写日志的对象  */
    private static final Log loger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserIconService userIconService;
    @Autowired
    private GroupService groupService;

    /**
     * Connecting with login.jsp input,
     * and init the data to view's input
     */
    @RequestMapping("/loginInput")
    public String inputUser(@ModelAttribute("user") UserEntity user, Model model) {
        return "login";
    }

    /**
     * redirect user to register.jsp
     */
    @RequestMapping("/regInput")
    public String regInput(@ModelAttribute("userPojo") userPojo userPojo, Model model) {
        return "register";
    }

    /**
     * redirect user to userPage.jsp
     */
    @RequestMapping("/success")
    public String redirectUser() {
        return "userPage";
    }

    /**
     * for user to log out
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/loginInput";
    }

    /**
     * HOMEPAGE
     */
    @RequestMapping("/userPage")
    public String userPage() {
        return "userPage";
    }


    /**
     * Handling Login event
     * Using object UserForm (entity bean) user to receive the args that registration page sends
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String Login(@ModelAttribute("user") UserEntity user, HttpSession session,
                        Model model, HttpServletRequest request) {
        // judge whether user input is empty
        if (user.getUserid() == 0 || user.getPassword() == null) {
            // log the error message
            loger.info("empty error");
            // use model to record the message
            model.addAttribute("tipMessage", "username or password could not be empty");
            // back tip JSON String
            return JSON.toJSONString("emptyInput");
        } else { // when userid and password is not empty
            Map<String, Object> userMap = new HashMap<>();
            // put user information into an HashMap
            userMap.put("userid", user.getUserid());
            userMap.put("password", user.getPassword());
            // take advantage of userService to handle userLogin event
            UserEntity userEntity = userService.userLogin(userMap);
            // when userid and password is correct
            if (userEntity != null) {
                // using session to record userEntity
                session.setAttribute("user", userEntity);
                // 将用户头像从数据库中取出，并放入session中
                // 如果没有头像，则不设置
                if(userIconService.whetherHasIcon(userEntity.getUserid())){
                    String imageSource = userIconService.getUserIcon(userEntity.getUserid());
                    session.setAttribute("userIcon", request.getContextPath() + imageSource);
                }
                // 将用户的分组信息放入session中
                if (session.getAttribute("userGroup")== null) {
                    userEntity = (UserEntity) session.getAttribute("user");
                    session.setAttribute("userGroup", groupService.getAllGroups(userEntity.getUserid()));
                }
                // log the success message
                loger.info("success");
                // back success JSON string
                return JSON.toJSONString("success");
            } else { // when either userid or password is wrong
                // log the error message
                loger.info("input error");
                // use model to record the message
                model.addAttribute("errorMessage", "username or password is incorrect");
                // back to Json String to user
                return JSON.toJSONString("errorInput");
            }
        }
    }


    /**
     * Handling Registration
     * Using object UserForm (entity bean) user to receive the args that registration page sends
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(@ModelAttribute("userPojo") userPojo userPojo, Model model,
                           GroupEntity groupEntity, UserEntity userEntity) throws SQLException {
        // regular expression for phone number
        String phonePattern = "^1(3|4|5|7|8)\\d{9}$";
        // regular expression for email
        String emailPattern = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

        //compile regular expression
        Pattern ePattern = Pattern.compile(emailPattern);
        Pattern pPattern = Pattern.compile(phonePattern);

        // if any inputs is empty ,back the error message
        if (userPojo.getUsername() == null || userPojo.getPassword() == null || userPojo.getRepassword() == null ||
                userPojo.getEmail() == null || userPojo.getPhone() == null) {
            // log the error message
            loger.info("empty error");
            // back tip JSON String
            return JSON.toJSONString("emptyInput");
            // if password unequal to rePassword ,back the error message
        } else if (!userPojo.getPassword().equals(userPojo.getRepassword())) {
            return JSON.toJSONString("unequalPassword");
            // if phone is illegal, back the error message
        } else if (!pPattern.matcher(userPojo.getPhone()).matches()) {
            return JSON.toJSONString("irregularPhone");
            // if email is illegal, back the error message
        } else if (!ePattern.matcher(userPojo.getEmail()).matches()) {
            return JSON.toJSONString("irregularEmail");
        } else if (userService.existPhone(userPojo.getPhone())) {
            return JSON.toJSONString("existPhone");
        } else if (userService.existEmail(userPojo.getEmail())) {
            return JSON.toJSONString("existEmail");
        } else {
            // generate 8 random digits
            int userId = Integer.parseInt(RandomNum.RandomNumber8());
            // if 8 random digits is repetitive,generate it again
            while (true) {
                if (userService.existId(userId)) {
                    userId = Integer.parseInt(RandomNum.RandomNumber8());
                } else {
                    break;
                }
            }
            userEntity.setUserid(userId);
            userEntity.setUsername(userPojo.getUsername());
            userEntity.setPassword(userPojo.getPassword());
            userEntity.setSex(userPojo.getSex());
            userEntity.setPhone(userPojo.getPhone());
            userEntity.setEmail(userPojo.getEmail());

            // 为用户创建默认分组
            groupEntity.setUserid(userId);
            groupEntity.setGroupname("defaultGroup");
            groupService.addGroup(groupEntity);

            Map<String, Integer> resMap = new HashMap<>();
            resMap.put("success", userId);

            return userService.userRegister(userEntity) == 1
                    ? JSON.toJSONString(resMap) : JSON.toJSONString("fail");

        }
    }
}
