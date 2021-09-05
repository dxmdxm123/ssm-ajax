package com.du.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * 该控制类是为了查找jsp或者带参数访问jsp,或者跳转的
 * */
@Controller
@RequestMapping("/pages")
public class PagesController {
    @RequestMapping("/reg")
    public String reg(){
        System.out.println("请求进入reg.....");
        return "reg";
    }

    @RequestMapping("/regForm")
    public String regForm(){
        return "regForm";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @RequestMapping("ajaxCommit")
    public String ajaxCommit(){
        return "ajaxCommit";
    }
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
