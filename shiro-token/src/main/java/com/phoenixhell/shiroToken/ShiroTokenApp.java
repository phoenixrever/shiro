package com.phoenixhell.shiroToken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.phoenixhell.shiroToken.mapper")
@SpringBootApplication
public class ShiroTokenApp {

    public static void main(String[] args) {
        SpringApplication.run(ShiroTokenApp.class, args);
    }
}
