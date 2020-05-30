package com.aaa.controller;

import com.aaa.biz.UserBiz;
import com.aaa.entity.LayUiTable;
import com.aaa.entity.MyUserInfo;
import com.aaa.entity.User;
import com.aaa.util.MyConstants;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 15:32
 * @Version 1.0
 * 用户管理
 */
@Controller
//此处的RequestMapping是窄化请求，1年级
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBiz userBizImpl;

    @RequestMapping("/toShowUser")
    @RequiresPermissions("system:user:view")
    public String toShowUserLayui() {
        return "user/showUser";
    }

    @RequestMapping("/showUser")
    @ResponseBody
    public LayUiTable showUserLayui(int page, int limit) {
        //开始查询
        PageInfo<User> pageInfo = userBizImpl.selectAllUser(page, limit);
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        //设置分页之后的返回值
        layUiTable.setCount(pageInfo.getTotal());
        layUiTable.setData(pageInfo.getList());
        return layUiTable;
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(User userInfo){
        int i = userBizImpl.insertSelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.saveSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.saveFailMsg);
        }
        return map;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(User userInfo){
        int i = userBizImpl.updateByPrimaryKeySelective(userInfo);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        return map;
    }
    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = userBizImpl.delUserByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }

}
