package com.aaa.controller;

import com.aaa.biz.MenuBiz;
import com.aaa.entity.LayUiTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/28 0028 6:59
 * @Version 1.0
 */
@Controller
public class MenuController {
    @Autowired
    private MenuBiz menuBiz;
    @RequestMapping("/toShowMenu")
    public String toShowMenu() {
        return "menu/showMenu";
    }
    @RequestMapping("/toShowMenu2")
    public String toShowMenu2() {
        return "menu/showMenu2";
    }
    @RequestMapping("/toShowMenuTree")
    public String showMenuTree() {
        return "menu/showMenuTree";
    }
    @RequestMapping("/toShowMenuTree2")
    public String showMenuTree2() {
        return "menu/showMenuTree2";
    }

    @RequestMapping("/selectAllMenu")
    @ResponseBody
    public List<LayUiTree> selectAllMenu(){
        List<LayUiTree> layUiTrees = menuBiz.selectAllMenu();
        return layUiTrees;
    }
/*
    @RequestMapping("/selectAllMenu")
    @ResponseBody
    public LayUiTree selectAllMenu(){
        List<LayUiTree> layUiTrees = menuBiz.selectAllMenu();
        LayUiTree layUiTree= new LayUiTree();
        layUiTree.setId(0);
        layUiTree.setTitle("总目录");
        layUiTree.setChildren(layUiTrees);
        return layUiTree;
    }
*/
}
