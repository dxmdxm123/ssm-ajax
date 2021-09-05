package com.du.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("请求进入hello。。。了");
        return "rello";  //故意写的 强调  必须和XXX rello
    }
    @RequestMapping("/reg")
    public String reg(){
        System.out.println("请求注册进入hello。。。了");
        return "reg";  //故意写的 强调  必须和XXX rello
    }
}