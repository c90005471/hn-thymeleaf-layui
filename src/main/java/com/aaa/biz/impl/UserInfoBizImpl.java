package com.aaa.biz.impl;

import com.aaa.biz.UserInfoBiz;
import com.aaa.dao.MyUserInfoMapper;
import com.aaa.entity.MyUserInfo;
import com.aaa.shiro.ShiroUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 15:49
 * @Version 1.0
 *
 * 请求=》controller=>service(biz)=>dao=>DB
 */
@Service
public class UserInfoBizImpl implements UserInfoBiz {
    @Autowired
    private MyUserInfoMapper myUserInfoMapper;

    @Override
    public PageInfo<MyUserInfo> selectAllUser(int page,int limit) {
    //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        List<MyUserInfo> myUserInfos = myUserInfoMapper.selectAllUser();
        //结束分页,pageInfo封装了分页之后所有数据
        PageInfo<MyUserInfo> pageInfo = new PageInfo(myUserInfos);
        return pageInfo;

    }

    /**
     * 按照用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public MyUserInfo selectUserByUsername(String username) {
        return myUserInfoMapper.selectUserByUsername(username);
    }

    @Override
    public int insertSelective(MyUserInfo record) {
        //次处要进行密码加盐加密
        String salt= UUID.randomUUID().toString();
        String message=record.getPassword();
        String encryption = ShiroUtil.encryptionBySalt(salt, message);
        record.setPassword(encryption);
        record.setSalt(salt);
        return myUserInfoMapper.insertSelective(record);
    }

    @Override
    public int delUserByID(List<String> ids) {
        return myUserInfoMapper.delUserByID( ids) ;
    }

    @Override
    public int updateByPrimaryKeySelective(MyUserInfo record) {
        return myUserInfoMapper.updateByPrimaryKeySelective(record);
    }
}
