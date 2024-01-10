package io.github.hdhxby.example.service.impl;

import io.github.hdhxby.example.domain.AuthRole;
import io.github.hdhxby.example.mapper.AuthRoleMapper;
import io.github.hdhxby.example.repository.AuthRoleRepository;
import io.github.hdhxby.example.repository.criteria.AuthRoleCriteria;
import io.github.hdhxby.example.service.AuthRoleService;
import io.github.hdhxby.example.service.dto.AuthRoleDTO;
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
