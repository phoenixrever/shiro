package com.phoenixhell.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author phoenixhell
 * @since 2021/10/5 0005-下午 4:50
 */
@Controller
public class LoginController {
    @GetMapping({"/","/index"})
    public String index(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            model.addAttribute("user", currentUser);
        }else{
            model.addAttribute("user", "未登录");
        }
        return "index";
    }

    @GetMapping({"/login.html"})
    public String loginPage() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:index";
        }
        return "login";
    }


    @GetMapping("/login")
    public String login(String username, String password,@RequestParam(defaultValue = "false") Boolean rememberMe,RedirectAttributes redirectAttributes) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                redirectAttributes.addFlashAttribute("error","没有此用户");
                return "redirect:login.html";
            } catch (IncorrectCredentialsException ice) {
                redirectAttributes.addFlashAttribute("error","密码错误");
                return "redirect:login.html";
            } catch (LockedAccountException lae) {
                redirectAttributes.addFlashAttribute("error","账户锁定");
                return "redirect:login.html";
            } catch (AuthenticationException ae) {
                redirectAttributes.addFlashAttribute("error","未知异常");
                return "redirect:login.html";
            }
        }
        return "redirect:index";
    }
}
