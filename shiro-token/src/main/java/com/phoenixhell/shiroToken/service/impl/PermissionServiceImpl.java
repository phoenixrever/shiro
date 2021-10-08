package com.phoenixhell.shiroToken.service.impl;

import com.phoenixhell.shiroToken.entity.Permission;
import com.phoenixhell.shiroToken.mapper.PermissionMapper;
import com.phoenixhell.shiroToken.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
