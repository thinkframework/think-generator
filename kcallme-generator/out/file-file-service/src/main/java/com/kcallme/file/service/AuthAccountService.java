package com.kcallme.file.service;

import com.hdhxby.ecommerce.auth.repository.criteria.AuthRoleCriteria;
import com.hdhxby.ecommerce.auth.service.dto.AuthRoleDTO;
import io.github.thinkframework.commons.service.CriteriaService;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限服务实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AuthAccountService extends CriteriaService<AuthAccountDTO, AuthAccountCriteria, Long> {
}
