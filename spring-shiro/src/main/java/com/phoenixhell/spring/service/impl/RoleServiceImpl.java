package com.phoenixhell.spring.service.impl;

import com.phoenixhell.spring.entity.Role;
import com.phoenixhell.spring.mapper.RoleMapper;
import com.phoenixhell.spring.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
