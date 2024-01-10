package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthAccount;
import com.hdhxby.ecommerce.auth.repository.AuthAccountRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthAccountMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthAccountRepositoryImpl extends AbstractRepository<AuthAccountMybatisMapper,
    AuthAccount, AuthAccountCriteria, Long> implements AuthAccountRepository {

}
