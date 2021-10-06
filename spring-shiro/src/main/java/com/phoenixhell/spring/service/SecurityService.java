package com.phoenixhell.spring.service;


import com.phoenixhell.spring.entity.Permission;
import com.phoenixhell.spring.entity.Role;
import com.phoenixhell.spring.entity.User;

import java.util.List;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:41
 */

public interface SecurityService {
    User findUserByName(String username);
    List<Permission> findPermissionsByUsername(String username);
    List<Role> findRoleByUsername(String username);
}
