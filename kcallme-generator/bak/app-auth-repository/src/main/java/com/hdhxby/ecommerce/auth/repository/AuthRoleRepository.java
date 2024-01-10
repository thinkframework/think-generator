package com.hdhxby.ecommerce.auth.repository;

import com.hdhxby.ecommerce.auth.domain.AuthRole;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRoleCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthRoleRepository extends CriteriaRepository<AuthRole, AuthRoleCriteria, Long> {

}
