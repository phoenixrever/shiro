package com.phoenixhell.spring.service.impl;

import com.phoenixhell.spring.entity.User;
import com.phoenixhell.spring.mapper.UserMapper;
import com.phoenixhell.spring.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phoenixhell
 * @since 2021-10-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
