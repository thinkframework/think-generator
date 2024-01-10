package com.kcallme.file.service.impl;

import com.kcallme.file.domain.AuthAccount;
import com.kcallme.file.mapper.AuthAccountMapper;
import com.kcallme.file.repository.AuthAccountRepository;
import com.kcallme.file.repository.criteria.AuthAccountCriteria;
import com.kcallme.file.service.AuthAccountService;
import com.kcallme.file.service.dto.AuthAccountDTO;
import io.github.thinkframework.commons.service.AbstractService;
import org.springframework.stereotype.Service;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限服务实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AuthAccountServiceImpl extends AbstractService<AuthAccountRepository, AuthAccountMapper,
    AuthAccount, AuthAccountDTO, AuthAccountCriteria, Long> implements AuthAccountService {

}
