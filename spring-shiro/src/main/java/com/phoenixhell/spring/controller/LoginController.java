package com.phoenixhell.spring.controller;

import com.phoenixhell.spring.excption.MyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenixhell
 * @since 2021/10/5 0005-下午 4:50
 */
@Controller
public class LoginController {
    @GetMapping({"/login.html", "/"})
    public String index() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
           currentUser.logout();
        }
        return "login";
    }

    @GetMapping("/login")
    public String login(String username, String password, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                throw new MyException(20000, e.getMessage());
            }
        }
        model.addAttribute("user", currentUser.getPrincipal());
        return "index";
    }
}
