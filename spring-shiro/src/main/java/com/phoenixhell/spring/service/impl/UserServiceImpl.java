package com.phoenixhell.spring.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phoenixhell.spring.entity.User;
import com.phoenixhell.spring.mapper.UserMapper;
import com.phoenixhell.spring.service.UserService;
import org.springframework.stereotype.Service;


/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:36
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
