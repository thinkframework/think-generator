package com.kcallme.file.service.impl;

import com.kcallme.file.domain.AuthRole;
import com.kcallme.file.mapper.AuthRoleMapper;
import com.kcallme.file.repository.AuthRoleRepository;
import com.kcallme.file.repository.criteria.AuthRoleCriteria;
import com.kcallme.file.service.AuthRoleService;
import com.kcallme.file.service.dto.AuthRoleDTO;
import io.github.thinkframework.commons.service.AbstractService;
import org.springframework.stereotype.Service;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限服务实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AuthRoleServiceImpl extends AbstractService<AuthRoleRepository, AuthRoleMapper,
    AuthRole, AuthRoleDTO, AuthRoleCriteria, Long> implements AuthRoleService {

}
