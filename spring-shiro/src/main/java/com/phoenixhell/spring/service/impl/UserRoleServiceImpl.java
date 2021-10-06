package com.phoenixhell.spring.service.impl;

import com.phoenixhell.spring.entity.UserRole;
import com.phoenixhell.spring.mapper.UserRoleMapper;
import com.phoenixhell.spring.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
