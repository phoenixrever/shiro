package com.phoenixhell.spring.realm;

import com.phoenixhell.spring.entity.Permission;
import com.phoenixhell.spring.entity.Role;
import com.phoenixhell.spring.service.SecurityService;
import com.phoenixhell.spring.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:16
 */

// realm 和数据库用户名密码比对
@Component
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private SecurityService securityService;

    @Value(value = "${salt}")
    private String salt;

    //授权给用户
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        System.out.println("授权给用户:"+username);

        List<Role> roles = securityService.findRoleByUsername(username);
        List<String> roleList = roles.stream().map(role -> role.getRoleName()).collect(Collectors.toList());

        List<Permission> permissions = securityService.findPermissionsByUsername(username);
        System.out.println(permissions);
        List<String> permissionList = permissions.stream().map(permission -> permission.getPermission()).collect(Collectors.toList());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    //自定义认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登陆名
        String loginName = (String) token.getPrincipal();
        User user = securityService.findUserByName(loginName);
        System.out.println(user);
        if (user == null) {
            // 在该方法中只允许抛出继承自AuthenticationException的异常
            throw new UnknownAccountException("用户不存在");
        }
        //第二个参数从数据库中获取的password，加密后再与token中的password进行对比，匹配上了就通过，匹配不上就报异常。
        //第三个参数 salt
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginName, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        return simpleAuthenticationInfo;
    }
}
