package com.dongdongshop.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //创建安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Autowired ShiroRealm shiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return  defaultWebSecurityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(@Autowired HashedCredentialsMatcher matcher){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }

    @Bean
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashIterations(10);
        matcher.setHashAlgorithmName("MD5");
        return matcher;
    }


}
