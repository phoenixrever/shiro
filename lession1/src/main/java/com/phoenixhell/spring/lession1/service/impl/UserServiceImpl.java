package com.phoenixhell.spring.lession1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phoenixhell.spring.lession1.entity.User;
import com.phoenixhell.spring.lession1.mapper.UserMapper;
import com.phoenixhell.spring.lession1.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:36
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
