package com.phoenixhell.spring.service.impl;


import com.phoenixhell.spring.entity.Permission;
import com.phoenixhell.spring.entity.Role;
import com.phoenixhell.spring.entity.UserRole;
import com.phoenixhell.spring.service.*;
import com.phoenixhell.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:43
 */
@Service("SecurityService")
public class SecurityServiceImpl implements SecurityService {
    //暂时无法注入先学后面的
    @Resource
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User findUserByName(String username) {
        User user = userService.query().eq("username", username).one();
        return user;
    }

    @Override
    public List<Permission> findPermissionsByUsername(String username) {
        List<Role> roles = findRoleByUsername(username);
        ArrayList<Permission> allPermissions = new ArrayList<>();
        roles.forEach(role ->{
            List<Permission> permissions = permissionService.query().eq("role_id", role.getId()).list();
            allPermissions.addAll(permissions);
        });
        return allPermissions;
    }

    @Override
    public List<Role> findRoleByUsername(String username) {
        User user = findUserByName(username);
        List<UserRole> userRoles = userRoleService.query().eq("user_id", user.getId()).list();
        List<Role> roles = userRoles.stream().map(userRole -> roleService.getById(userRole.getRoleId())).collect(Collectors.toList());
        return roles;
    }
}
