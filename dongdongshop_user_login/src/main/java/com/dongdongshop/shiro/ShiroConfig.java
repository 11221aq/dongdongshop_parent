package com.dongdongshop.shiro;

import com.dongdongshop.util.JwtUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //引入安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //设置拦截器
        Map<String, String> map = new LinkedHashMap<>();
        //先放过登录
        map.put("/userLogin","anon");
        map.put("/userWeibo/toLogin","anon");
        map.put("/userWeibo/callback","anon");
        map.put("/toLogin","anon");
        //设置退出登录
        map.put("/logout","logout");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Autowired LoginRealm loginRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(loginRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public LoginRealm loginRealm(@Autowired HashedCredentialsMatcher matcher){
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(matcher);
        return loginRealm;
    }

    @Bean
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(10);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

}
