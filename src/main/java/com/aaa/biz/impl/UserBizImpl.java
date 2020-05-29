package com.aaa.biz.impl;

import com.aaa.biz.UserBiz;
import com.aaa.dao.UserMapper;
import com.aaa.entity.User;
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
 * <p>
 * 请求=》controller=>service(biz)=>dao=>DB
 */
@Service
public class UserBizImpl implements UserBiz{
        @Autowired
        private UserMapper userMapper;
        @Override
        public PageInfo<User> selectAllUser(int page, int limit) {
            //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
            PageHelper.startPage(page, limit);
            List<User> myUserInfos = userMapper.selectAllUser();
            //结束分页,pageInfo封装了分页之后所有数据
            PageInfo<User> pageInfo = new PageInfo(myUserInfos);
            return pageInfo;

        }

        /**
         * 按照用户名查询用户信息
         *
         * @param username
         * @return
         */
        @Override
        public User selectUserByUsername(String username) {

            return userMapper.selectUserByUsername(username);
        }

        @Override
        public int insertSelective(User record) {
            //次处要进行密码加盐加密
            String salt = UUID.randomUUID().toString();
            String message = record.getPassword();
            String encryption = ShiroUtil.encryptionBySalt(salt, message);
            record.setPassword(encryption);
            record.setSalt(salt);
            return userMapper.insertSelective(record);
        }

        @Override
        public int delUserByID(List<String> ids) {
            return userMapper.delUserByID(ids);
        }

        @Override
        public int updateByPrimaryKeySelective(User record) {
            return userMapper.updateByPrimaryKeySelective(record);
        }
}
