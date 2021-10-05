package com.phoenixhell.spring.service;


import com.phoenixhell.spring.entity.User;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:41
 */

public interface SecurityService {
    User findUserByName(String name);
}
