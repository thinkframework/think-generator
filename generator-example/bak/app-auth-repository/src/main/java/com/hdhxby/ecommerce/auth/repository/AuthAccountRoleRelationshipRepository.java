package com.hdhxby.ecommerce.auth.repository;

import com.hdhxby.ecommerce.auth.domain.AuthAccountRoleRelationship;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountRoleRelationshipCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 账号和角色的关系表，一个账号可以对应多个角色，一个角色也可以对应多个账号仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthAccountRoleRelationshipRepository extends CriteriaRepository<AuthAccountRoleRelationship, AuthAccountRoleRelationshipCriteria, Long> {

}
