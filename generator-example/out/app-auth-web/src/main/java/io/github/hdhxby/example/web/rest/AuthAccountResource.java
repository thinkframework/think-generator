package io.github.hdhxby.example.repository;

import io.github.hdhxby.example.repository.criteria.AuthAccountCriteria;
import io.github.hdhxby.example.service.dto.AuthAccountDTO;
import io.github.thinkframework.commons.web.rest.CriteriaResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@RequestMapping("/api/auth/auth-AuthAccounts")
public interface AuthAccountResource extends CriteriaResource<AuthAccountDTO, AuthAccountCriteria, Long> {

}
