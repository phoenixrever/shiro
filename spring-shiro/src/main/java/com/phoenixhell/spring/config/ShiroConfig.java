package com.phoenixhell.spring.config;

import com.phoenixhell.spring.realm.LoginRealm;
import com.phoenixhell.spring.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
    @Autowired
    private MyRealm myRealm;

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

        //=======================设置多认证策略===========================
        //如果将多个realm注入SecurityManager 一定要先设置多realm管理器modularRealmAuthenticator
        //不然会 No realms have been configured! 异常
        //ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        //modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        //securityManager.setAuthenticator(modularRealmAuthenticator);

        //ArrayList<Realm> realms = new ArrayList<>();
        //realms.add(loginRealm);
        //realms.add(myRealm);
        //securityManager.setRealms(realms);
        //=======================设置多认证策略  完成===========================

        securityManager.setRealm(loginRealm);
        //设置rememberMe manager
        securityManager.setRememberMeManager(cookieRememberMeManager());

        return securityManager;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition=new DefaultShiroFilterChainDefinition();
        // 登入功能
        chainDefinition.addPathDefinition("/login","anon");
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

    //cookie 设置
    @Bean
    public SimpleCookie rememberMeCookie(){
        //对应前端的checkbox的name = rememberMe
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //cookie.setDomain("gulimall.com");
        // "/"路径下的所有路径cookie都起效
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //cookie 失效时间30天
        cookie.setMaxAge(60*60*24*30 );
        return cookie;
    }

    //rememberMe manager
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //对cookie信息进行加密设置  需要16位
        cookieRememberMeManager.setCipherKey("cookiecookiecook".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
}
