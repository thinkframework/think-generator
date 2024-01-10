package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthRolePriorityRelationship;
import com.hdhxby.ecommerce.auth.repository.AuthRolePriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRolePriorityRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthRolePriorityRelationshipMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色和权限的关系表，角色和权限是多对多的关系，一个角色可以对应多个权限，一个权限可以属于多个角色仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthRolePriorityRelationshipRepositoryImpl extends AbstractRepository<AuthRolePriorityRelationshipMybatisMapper,
    AuthRolePriorityRelationship, AuthRolePriorityRelationshipCriteria, Long> implements AuthRolePriorityRelationshipRepository {

}
