package com.aaa.shiro;

import com.aaa.util.MyConstants;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 陈建
 * @Date: 2020/5/25 0025 16:56
 * @Version 1.0
 * shiro安全框架的配置类，相当于之前的shiro.xml
 */
@Configuration
public class ShiroConfig {
    /**
     * 定义一个bean，id为方法名
     * <bean id="fdsaf" class=">
     *
     *     </bean>
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }
    /**
     * securityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager= new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }
    /**
     * shiroFilterFactorybean
     * shiro的安全过滤器，过滤所有的请求，对请求分类拦截
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
/**
 * 认证过滤器的分类
 * anon:无需认证
 * authc:必须认证才能到达
 * user:使用rememberme的时候才用
 * perms：访问的资源需要某个权限才能到达
 * roles:访问的资源需要某个角色才能到达
 */
        Map<String, String> map = new LinkedHashMap<>();
        //放行login
        map.put("/login","anon");
        //过滤所有的请求
        map.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //修改登录页面，所有的未认证的请求都给我滚，滚去登录
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }
    /**
     * 实例化密码比较器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher= new HashedCredentialsMatcher();
        //使用md5加密
        credentialsMatcher.setHashAlgorithmName(MyConstants.algorithmName);
        //加密1000次
        credentialsMatcher.setHashIterations(MyConstants.hashIterations);
        return credentialsMatcher;
    }
}
