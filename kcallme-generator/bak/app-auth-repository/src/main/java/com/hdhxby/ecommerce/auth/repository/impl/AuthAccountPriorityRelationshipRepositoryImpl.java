package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthAccountPriorityRelationship;
import com.hdhxby.ecommerce.auth.repository.AuthAccountPriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthAccountPriorityRelationshipMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号与权限的关系表，一个账号可以对应多个权限，一个权限也可以属于多个账号仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthAccountPriorityRelationshipRepositoryImpl extends AbstractRepository<AuthAccountPriorityRelationshipMybatisMapper,
    AuthAccountPriorityRelationship, AuthAccountPriorityRelationshipCriteria, Long> implements AuthAccountPriorityRelationshipRepository {

}
