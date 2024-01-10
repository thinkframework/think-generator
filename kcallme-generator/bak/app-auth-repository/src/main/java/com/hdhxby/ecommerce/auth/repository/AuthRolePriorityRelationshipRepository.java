package com.hdhxby.ecommerce.auth.repository;

import com.hdhxby.ecommerce.auth.domain.AuthRolePriorityRelationship;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRolePriorityRelationshipCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 角色和权限的关系表，角色和权限是多对多的关系，一个角色可以对应多个权限，一个权限可以属于多个角色仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthRolePriorityRelationshipRepository extends CriteriaRepository<AuthRolePriorityRelationship, AuthRolePriorityRelationshipCriteria, Long> {

}
