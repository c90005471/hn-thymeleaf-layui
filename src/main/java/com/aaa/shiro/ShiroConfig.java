package com.aaa.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.aaa.util.MyConstants;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
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
        map.put("/css/**","anon");
        map.put("/img/**","anon");
        map.put("/js/**","anon");
        map.put("/json/**","anon");
        map.put("/layui/**","anon");

        //过滤所有的请求
        map.put("/**","authc");
        //授权页面
       /* map.put("/user/toShowUser","perms[system:user:view]");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //修改登录页面，所有的未认证的请求都给我滚，滚去登录
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //指定未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUnau");
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
    /**
     * 设置shiro的方言
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
