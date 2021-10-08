package com.phoenixhell.shiroToken.service.impl;

import com.phoenixhell.shiroToken.entity.User;
import com.phoenixhell.shiroToken.mapper.UserMapper;
import com.phoenixhell.shiroToken.service.UserService;
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
