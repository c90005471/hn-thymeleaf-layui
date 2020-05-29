package com.aaa.biz.impl;

import com.aaa.biz.MenuBiz;
import com.aaa.dao.MenuMapper;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/28 0028 11:38
 * @Version 1.0
 */
@Service
public class MenuBizImpl implements MenuBiz {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public int deleteByPrimaryKey(Integer menuId) {
        return 0;
    }

    @Override
    public int insert(Menu record) {
        return 0;
    }

    @Override
    public int insertSelective(Menu record) {
        return 0;
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return 0;
    }

    /**
     * 查询所有的菜单，并组装成tree格式的
     * @return
     */
    @Override
    public List<LayUiTree> selectAllMenu() {
        //查询所有的菜单
        List<Menu> menus = menuMapper.selectAllMenu();
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }

    @Override
    public List<LayUiTree> selectAllMenuByName(String loginName) {
        //查询所有的菜单
        List<Menu> menus = menuMapper.selectAllMenuByName(loginName);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }
}
