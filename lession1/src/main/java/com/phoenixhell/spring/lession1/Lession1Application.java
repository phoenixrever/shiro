package com.phoenixhell.spring.lession1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.phoenixhell.lession1.mapper")
@SpringBootApplication
public class Lession1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lession1Application.class, args);
    }

}
