package com.phoenixhell.spring.config;

import com.phoenixhell.spring.realm.LoginRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author phoenixhell
 * @since 2021/10/5 0005-下午 4:56
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private LoginRealm loginRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入凭证匹配器
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //指定加密 算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //指定散列次数
        credentialsMatcher.setHashIterations(2);
        loginRealm.setCredentialsMatcher(credentialsMatcher);
        //将realm注入SecurityManager
        securityManager.setRealm(loginRealm);
        return securityManager;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition=new DefaultShiroFilterChainDefinition();
        // 登出功能
        chainDefinition.addPathDefinition("/logout","logout");
        // 错误页面无需认证
        chainDefinition.addPathDefinition("/error","anon");
        // druid连接池的角色控制，只有拥有admin角色的admin用户可以访问，不理解可以先不管
        chainDefinition.addPathDefinition("/druid/**","authc, roles[admin]");
        // 静态资源无需认证
        chainDefinition.addPathDefinition("/static/**","anon");
        // 其余资源都需要认证
        chainDefinition.addPathDefinition("/**","authc");
        return chainDefinition;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
}
