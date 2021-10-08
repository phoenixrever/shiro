package com.phoenixhell.shiroToken.service;


import com.phoenixhell.shiroToken.entity.Permission;
import com.phoenixhell.shiroToken.entity.Role;
import com.phoenixhell.shiroToken.entity.User;

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
