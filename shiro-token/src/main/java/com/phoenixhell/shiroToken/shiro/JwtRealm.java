package com.phoenixhell.shiroToken.shiro;

import com.phoenixhell.shiroToken.entity.Permission;
import com.phoenixhell.shiroToken.entity.Role;
import com.phoenixhell.shiroToken.entity.User;
import com.phoenixhell.shiroToken.service.SecurityService;
import com.phoenixhell.shiroToken.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private SecurityService securityService;

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("JwtRealm");
        String jwtToken = (String) authenticationToken.getPrincipal();
        if (jwtToken == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }
        //判断
        JwtUtil jwtUtil = new JwtUtil();
        if (!jwtUtil.isVerify(jwtToken)) {
            throw new UnknownAccountException("用户不存在");
        }
        //判断数据库中username是否存在
        String username = (String) jwtUtil.decode(jwtToken).get("username");
        User user = securityService.findUserByName(username);
        System.out.println(user);
        if (user == null) {
            // 在该方法中只允许抛出继承自AuthenticationException的异常
            throw new UnknownAccountException("用户不存在");
        }
        log.info("在使用token登录"+username);
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名
        return new SimpleAuthenticationInfo(jwtToken,jwtToken,getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("授权给用户:"+username);

        List<Role> roles = securityService.findRoleByUsername(username);
        List<String> roleList = roles.stream().map(role -> role.getRoleName()).collect(Collectors.toList());

        List<Permission> permissions = securityService.findPermissionsByUsername(username);
        System.out.println(permissions);
        List<String> permissionList = permissions.stream().map(permission -> permission.getPermission()).collect(Collectors.toList());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }
}
