package com.mao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/login")
    public String login(){
        return "redirect:/user/loginInput";
    }
    @RequestMapping("/register")
    public String register(){
        return "redirect:/user/regInput";
    }

}
