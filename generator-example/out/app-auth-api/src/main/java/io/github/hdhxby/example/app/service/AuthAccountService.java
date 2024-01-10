package io.github.hdhxby.example.service;

import io.github.hdhxby.example.repository.criteria.AuthAccountCriteria;
import io.github.hdhxby.example.service.dto.AuthAccountDTO;
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
