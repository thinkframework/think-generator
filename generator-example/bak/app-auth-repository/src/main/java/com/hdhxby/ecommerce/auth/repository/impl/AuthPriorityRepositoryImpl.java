package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthPriority;
import com.hdhxby.ecommerce.auth.repository.AuthPriorityRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthPriorityCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthPriorityMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 权限表，每个权限代表了系统中的一个菜单、按钮、URL请求仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthPriorityRepositoryImpl extends AbstractRepository<AuthPriorityMybatisMapper,
    AuthPriority, AuthPriorityCriteria, Long> implements AuthPriorityRepository {

}
