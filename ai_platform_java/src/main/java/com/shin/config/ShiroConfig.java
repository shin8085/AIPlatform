package com.shin.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Subject 用户
 * SecurityManager 管理所有用户
 * Realm 连接数据库
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        /*
          anon：无需认证即可访问
          authc：必须认证才能访问
          user：必须拥有 记住我 功能才能使用
          perms：拥有对某个资源的权限才能访问
          role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/applyManage/getApplyInfo","authc");
        filterMap.put("/userManage/*","perms[administrator]");
        filterMap.put("/applyManage/*","perms[administrator]");
        filterMap.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);

//        //设置登入的请求
//        factoryBean.setLoginUrl("/login");

        return factoryBean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "manager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm对象，需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
