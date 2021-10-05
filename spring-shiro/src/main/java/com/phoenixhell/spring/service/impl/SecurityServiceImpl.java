package com.phoenixhell.spring.service.impl;


import com.phoenixhell.spring.entity.User;
import com.phoenixhell.spring.service.SecurityService;
import com.phoenixhell.spring.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:43
 */
@Service("SecurityService")
public class SecurityServiceImpl implements SecurityService {
    //暂时无法注入先学后面的
    @Resource
    private UserService userService;

    @Override
    public User findUserByName(String name) {
//        User user = userService.query().eq("name", name).one();
        User user = new User();
        user.setName("admin");
        user.setPassword("00b3187384f2708025074f28764a4a30");
        return user;
    }
}
