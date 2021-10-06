package com.phoenixhell.spring.service.impl;

import com.phoenixhell.spring.entity.Permission;
import com.phoenixhell.spring.mapper.PermissionMapper;
import com.phoenixhell.spring.service.PermissionService;
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
