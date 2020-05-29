package com.aaa.biz.impl;

import com.aaa.biz.RoleBiz;
import com.aaa.dao.RoleMapper;
import com.aaa.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/29 0029 16:36
 * @Version 1.0
 */
@Service
public class RoleBizImpl implements RoleBiz {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }
}
