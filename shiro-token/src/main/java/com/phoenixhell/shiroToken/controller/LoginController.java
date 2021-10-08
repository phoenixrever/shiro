package com.phoenixhell.shiroToken.controller;

import com.phoenixhell.shiroToken.util.AuthCodeUtil;
import com.phoenixhell.shiroToken.util.JwtUtil;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phoenixhell
 * @since 2021/10/5 0005-下午 4:50
 */
@Controller
public class LoginController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("isPermitted:" + currentUser.isPermitted("user:add"));
        if (currentUser.isAuthenticated()) {
            model.addAttribute("user", currentUser);
        } else {
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
    public String login(String username, String password,
                        @RequestParam(defaultValue = "false") Boolean rememberMe,
                        RedirectAttributes redirectAttributes,
                        String code,
                        HttpSession httpSession
    ) {
        //if(!code.equalsIgnoreCase((String) httpSession.getAttribute("code"))){
        //    redirectAttributes.addFlashAttribute("error", "验证码错误");
        //    return "redirect:login.html";
        //}

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                redirectAttributes.addFlashAttribute("error", "没有此用户");
                return "redirect:login.html";
            } catch (IncorrectCredentialsException ice) {
                redirectAttributes.addFlashAttribute("error", "密码错误");
                return "redirect:login.html";
            } catch (LockedAccountException lae) {
                redirectAttributes.addFlashAttribute("error", "账户锁定");
                return "redirect:login.html";
            } catch (AuthenticationException ae) {
                redirectAttributes.addFlashAttribute("error", "未知异常");
                return "redirect:login.html";
            }
        }
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        String jwtToken = jwtUtil.encode(username, 5 * 60 * 1000, chaim);
        System.out.println(jwtToken);
        return "redirect:index";
    }

    @GetMapping("/code")
    public void code(HttpSession httpSession, HttpServletResponse response) throws IOException {
        String authCode = AuthCodeUtil.getAuthCode();
        httpSession.setAttribute("code", authCode);
        BufferedImage authImg = AuthCodeUtil.getAuthImg(authCode);
        response.setContentType("image/png");
        ImageIO.write(authImg, "jpeg", response.getOutputStream());
    }
}
