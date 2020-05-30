package com.aaa.shiro;

import com.aaa.biz.MenuBiz;
import com.aaa.biz.UserBiz;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.MyUserInfo;
import com.aaa.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: 陈建
 * @Date: 2020/5/25 0025 16:33
 * @Version 1.0
 */
public class MyRealm  extends AuthorizingRealm {
    @Autowired
    private UserBiz userBizImpl;
    @Autowired
    private MenuBiz menuBiz;
    /**
     * 市容安全框架的授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权开始");
        User user = (User)  principals.getPrimaryPrincipal();
        // 菜单列表
        Set<String> perms = menuBiz.selectAllPermsByName(user.getLoginName());
        System.out.println(perms.toString());
        //将权限字符串添加到授权对象中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        return info;
    }
    /**
     * shiro安全框架的认证,
     * @param token
     * @return  AuthenticationInfo  ，假如返回的是null就说明认证失败
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证开始");
        //开始校验用户名和密码
        //取出令牌信息
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        //登录验证分两个步骤，步骤一查询用户是否存在
        String username=usernamePasswordToken.getUsername();
        User userInfo = userBizImpl.selectUserByUsername(username);
        if(null==userInfo){
            return null;
        }
        //步骤二，查询密码是否正确
            //数据库中的密码
        String password=userInfo.getPassword();
        //Object principal, Object credentials, String realmName
        /**
         *  * @param principal         the 'primary' principal associated with the specified realm.
         *      * @param hashedCredentials the hashed credentials that verify the given principal.
         *      * @param credentialsSalt   the salt used when hashing the given hashedCredentials
         *      * @param realmName         the realm from where the principal and credentials were acquired.
         */
        String salt = userInfo.getSalt();
        ByteSource byteSource=ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo= new SimpleAuthenticationInfo(userInfo,password,byteSource,getName());
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }
}
