package com.hdhxby.ecommerce.auth.web.rest.impl;

import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountRoleRelationshipCriteria;
import com.hdhxby.ecommerce.auth.service.*;
import com.hdhxby.ecommerce.auth.service.dto.*;
import com.hdhxby.ecommerce.auth.web.rest.AuthAccountResource;
import io.github.thinkframework.commons.repository.filter.LongFilter;
import io.github.thinkframework.commons.web.rest.AbstractRestResource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限资源控制器.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
@Api(tags = {"账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限"})
@RequestMapping("/api/auth/auth-accounts")
@RestController
public class AuthAccountResourceImpl extends AbstractRestResource<AuthAccountService,
    AuthAccountDTO, AuthAccountCriteria, Long> implements AuthAccountResource {

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthAccountRoleRelationshipService authAccountRoleRelationshipService;

    @Autowired
    private AuthPriorityService authPriorityService;

    @Autowired
    private AuthAccountPriorityRelationshipService authAccountPriorityRelationshipService;

    /**
     * 创建
     *
     * @param role 数据传输对象
     * @return
     */
    @Override
    public ResponseEntity<AuthRoleDTO> createRole(@PathVariable Long accountId, @RequestBody AuthRoleDTO role){
        authAccountRoleRelationshipService.create(AuthAccountRoleRelationshipDTO.builder()
            .accountId(accountId)
            .roleId(role.getId())
            .build());
        return ResponseEntity.ok(role);
    }

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @Override
    public ResponseEntity<List<AuthRoleDTO>> findAllRole(@PathVariable Long accountId){
        return ResponseEntity.ok(authAccountRoleRelationshipService.findAllByCriteria(AuthAccountRoleRelationshipCriteria.builder()
            .accountId(new LongFilter(accountId))
            .build())
            .stream().map(relationship -> authRoleService.findById(relationship.getRoleId()).get())
            .collect(Collectors.toList()));

    }

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @Override
    public ResponseEntity<AuthRoleDTO> findRoleById(@PathVariable Long accountId, @PathVariable(name = "id") Long id){
        return ResponseEntity.of(authRoleService.findById(id));
    }

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @Override
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long accountId, @PathVariable(name = "id") Long id){
        authAccountRoleRelationshipService.delete(AuthAccountRoleRelationshipDTO.builder()
            .accountId(accountId)
            .roleId(id)
            .build());
        return ResponseEntity.noContent().build();
    }


    /**
     * 创建
     *
     * @param priority 数据传输对象
     * @return
     */
    @Override
    public ResponseEntity<AuthPriorityDTO> createPriority(@PathVariable Long accountId, @RequestBody AuthPriorityDTO priority){
        authAccountPriorityRelationshipService.create(AuthAccountPriorityRelationshipDTO.builder()
            .accountId(accountId)
            .priorityId(priority.getId())
            .build());
        return ResponseEntity.ok(priority);
    }

    /**
     * 查找
     *
     * @param accountId id
     * @return
     */
    @Override
    public ResponseEntity<List<AuthPriorityDTO>> findAllPriority(@PathVariable Long accountId){
        return ResponseEntity.ok(authAccountPriorityRelationshipService.findAllByCriteria(AuthAccountPriorityRelationshipCriteria.builder()
                .accountId(new LongFilter(accountId))
                .build())
            .stream().map(relationship -> authPriorityService.findById(relationship.getPriorityId()).get())
            .collect(Collectors.toList()));
    }

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @Override
    public ResponseEntity<AuthPriorityDTO> findPriorityById(@PathVariable Long accountId, @PathVariable(name = "id") Long id){
        return ResponseEntity.of(authPriorityService.findById(id));
    }

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @Override
    public ResponseEntity<Void> deletePriorityById(@PathVariable Long accountId, @PathVariable(name = "id") Long id){
        authAccountPriorityRelationshipService.delete(AuthAccountPriorityRelationshipDTO.builder()
            .accountId(accountId)
            .priorityId(id)
            .build());
        return ResponseEntity.noContent().build();
    }
}
