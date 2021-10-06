package com.phoenixhell.spring.lession1.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:47
 */

@Slf4j
@RestController
public class ShiroController {
    @GetMapping("login")
    public String login() {
        //since 1.4  1.5为 BasicIniEnvironment("classpath:shiro.ini");
        //1 导入INI 配置创建工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2 工厂构建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //3 使用工具生效安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4 使用工具获得subject主体
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                //if no exception, that's it, we're done!
            } catch (UnknownAccountException uae) {
                //username wasn't in the system, show them an error message?
            } catch (IncorrectCredentialsException ice) {
                //password didn't match, try again?
            } catch (LockedAccountException lae) {
                //account for that username is locked - can't login.  Show them a message
            } catch (AuthenticationException ae) {
                //unexpected condition - error?
            }
        }

        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        if (currentUser.hasRole("admin")) {
            log.info("拥有admin角色!");
        } else {
            log.info("Hello,没有admin角色.");
        }

        //currentUser.checkPermission(); 没有会报错
        //isPermitted 返回bool 值
        if (currentUser.isPermitted("user:delete")) {
            log.info("拥有user下的删除权限");
        } else {
            log.info("Sorry, 没有user下的权限");
        }

        if (currentUser.isPermitted("user:delete:tom")) {
            log.info("拥有删除 user下 tom的权限");
        } else {
            log.info("Sorry, you aren't allowed to 删除tom!");
        }
        //all done - log out!
        //currentUser.logout();
        return "hello";
    }
}

