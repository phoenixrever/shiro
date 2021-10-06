package com.phoenixhell.spring.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenixhell
 * @since 2021/10/6 0006-下午 2:33
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @RequiresPermissions("user:add")
    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @RequiresPermissions("user:delete")
    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @RequiresPermissions("user:update")
    @GetMapping("/update")
    public String update(){
        return "update";
    }

    @RequiresPermissions("user:search")
    @GetMapping("/search")
    public String search(){
        return "search";
    }
}
