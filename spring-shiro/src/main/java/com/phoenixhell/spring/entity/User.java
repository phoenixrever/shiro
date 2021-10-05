package com.phoenixhell.spring.entity;

import lombok.Data;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:30
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}
