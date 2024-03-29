package com.phoenixhell.spring.lession1.realm;

import com.phoenixhell.spring.lession1.entity.User;
import com.phoenixhell.spring.lession1.excption.MyException;
import com.phoenixhell.spring.lession1.service.impl.SecurityServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:16
 */

// realm 保存用户名密码

public class LoginRealm extends AuthorizingRealm {
    //无法注入的自己new个 先解决
//    @Autowired
//    private SecurityService securityService;

    //鉴权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    //自定义认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登陆名
        String loginName = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        User user = securityService.findUserByName(loginName);


        if (user==null) {
             throw new MyException(2000,"用户不存在或者账号密码错误");
            // 在该方法中只允许抛出继承自AuthenticationException的异常
            //throw new UnknownAccountException("用户不存在");
        }
        //第二个参数从数据库中获取的password，加密后再与token中的password进行对比，匹配上了就通过，匹配不上就报异常。
        //第三个参数 salt
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginName, user.getPassword(), ByteSource.Util.bytes("salt"),getName());
        return simpleAuthenticationInfo;
    }
}
