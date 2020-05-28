package com.aaa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 陈建
 * @Date: 2020/5/26 0026 14:45
 * @Version 1.0
 */
@Controller
public class LoginController {
    /**
     * 将请求toLogin转发到登录页面login.html
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //登录验证
        System.out.println(username+password);
        //获取shiro的主体
        Subject subject = SecurityUtils.getSubject();
        //构建用户登录令牌
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        //AuthenticationToken token
        //subject.login(token);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            return "login";
        }
        return "user/showUserLayui";
    }
}
