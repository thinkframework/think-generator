package io.github.hdhxby.example.repository.impl;

import io.github.hdhxby.example.domain.AuthRole;
import io.github.hdhxby.example.repository.AuthRoleRepository;
import io.github.hdhxby.example.repository.criteria.AuthRoleCriteria;
import io.github.hdhxby.example.repository.mapper.AuthRoleMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthRoleRepositoryImpl extends AbstractRepository<AuthRoleMybatisMapper,
    AuthRole, AuthRoleCriteria, Long> implements AuthRoleRepository {

}
