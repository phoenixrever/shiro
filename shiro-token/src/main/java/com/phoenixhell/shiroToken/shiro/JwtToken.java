package com.phoenixhell.shiroToken.shiro;

import org.apache.shiro.authc.AuthenticationToken;

//这个就类似UsernamePasswordToken
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    //类似是用户名
    @Override
    public Object getPrincipal() {
        return jwt;
    }

    //类似密码
    @Override
    public Object getCredentials() {
        //返回的都是jwt
        return jwt;
    }

}