package com.hdhxby.ecommerce.auth.repository;

import com.hdhxby.ecommerce.auth.domain.AuthAccount;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthAccountRepository extends CriteriaRepository<AuthAccount, AuthAccountCriteria, Long> {

}
