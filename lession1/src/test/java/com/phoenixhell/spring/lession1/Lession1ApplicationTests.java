package com.phoenixhell.spring.lession1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class Lession1ApplicationTests {

    @Test
    void shiroLogin() {
        // 迭代加密2次  加密之后的字符串在加密
        String foo = new Md5Hash("123456","salt",2).toHex();
        System.out.println("二次带盐加密的结果："+foo);
    }
}
