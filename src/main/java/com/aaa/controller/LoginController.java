package com.aaa.controller;

import com.aaa.biz.MenuBiz;
import com.aaa.entity.LayUiTree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/26 0026 14:45
 * @Version 1.0
 */
@Controller
public class LoginController {
    @Autowired
    private MenuBiz menuBiz;
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
        //将要去index页面之前，保存部分数据到model
        model.addAttribute("loginName",username);
        //放入所有的菜单，根据当前登录的用户
        List<LayUiTree> menus = menuBiz.selectAllMenuByName(username);
        model.addAttribute("menus",menus);
        return "index";
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
