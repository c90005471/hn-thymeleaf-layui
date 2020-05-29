package com.aaa.controller;

import com.aaa.biz.RoleBiz;
import com.aaa.entity.LayUiTable;
import com.aaa.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/29 0029 16:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleBiz roleBizImpl;
    @RequestMapping("/toShowRole")
    public String  toShowRole(){
        return "role/showRole";
    }

    @RequestMapping("/showRole")
    @ResponseBody
    public LayUiTable showRoleLayui() {
        //开始查询
        List<Role> roleList = roleBizImpl.selectAllRole();
        LayUiTable layUiTable = new LayUiTable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        layUiTable.setData(roleList);
        return layUiTable;
    }
}
