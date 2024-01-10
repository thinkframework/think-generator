package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthAccountRoleRelationship;
import com.hdhxby.ecommerce.auth.repository.AuthAccountRoleRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountRoleRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthAccountRoleRelationshipMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号和角色的关系表，一个账号可以对应多个角色，一个角色也可以对应多个账号仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthAccountRoleRelationshipRepositoryImpl extends AbstractRepository<AuthAccountRoleRelationshipMybatisMapper,
    AuthAccountRoleRelationship, AuthAccountRoleRelationshipCriteria, Long> implements AuthAccountRoleRelationshipRepository {

}
