package com.phoenixhell.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.phoenixhell.spring.mapper")
@SpringBootApplication
public class SpringShiroApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringShiroApp.class, args);
    }

}
