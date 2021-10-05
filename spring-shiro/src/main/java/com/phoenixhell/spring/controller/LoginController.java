package com.phoenixhell.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenixhell
 * @since 2021/10/5 0005-下午 4:50
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
