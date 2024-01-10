package com.hdhxby.ecommerce.auth.repository;

import com.hdhxby.ecommerce.auth.domain.AuthPriority;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthPriorityCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 权限表，每个权限代表了系统中的一个菜单、按钮、URL请求仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthPriorityRepository extends CriteriaRepository<AuthPriority, AuthPriorityCriteria, Long> {

}
