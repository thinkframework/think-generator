package io.github.hdhxby.example.web.rest;

import io.github.hdhxby.example.repository.criteria.AuthAccountCriteria;
import io.github.hdhxby.example.service.dto.AuthAccountDTO;
import io.github.hdhxby.example.service.dto.AuthPriorityDTO;
import io.github.hdhxby.example.service.dto.AuthRoleDTO;
import io.github.thinkframework.commons.web.rest.CriteriaResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
@RequestMapping("/api/auth/auth-accounts")
public interface  AuthAccountControler extends CriteriaResource<AuthAccountDTO, AuthAccountCriteria, Long> {

    /**
     * 创建
     *
     * @param role 数据传输对象
     * @return
     */
    @PostMapping("{accountId}/roles")
    ResponseEntity<AuthRoleDTO> createRole(@PathVariable Long accountId, @RequestBody AuthRoleDTO role);

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @GetMapping("{accountId}/roles")
    ResponseEntity<List<AuthRoleDTO>> findAllRole(@PathVariable Long accountId);

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @GetMapping("{accountId}/roles/{id}")
    ResponseEntity<AuthRoleDTO> findRoleById(@PathVariable Long accountId, @PathVariable(name = "id") Long id);

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @ApiOperation("删除")
    @DeleteMapping("{accountId}/roles/{id}")
    ResponseEntity<Void> deleteRoleById(@PathVariable Long accountId, @PathVariable(name = "id") Long id);


    /**
     * 创建
     *
     * @param priority 数据传输对象
     * @return
     */
    @PostMapping("{accountId}/prioritys")
    ResponseEntity<AuthPriorityDTO> createPriority(@PathVariable Long accountId, @RequestBody AuthPriorityDTO priority);


    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @GetMapping("{accountId}/prioritys")
    ResponseEntity<List<AuthPriorityDTO>> findAllPriority(@PathVariable Long accountId);

    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @GetMapping("{accountId}/prioritys/{id}")
    ResponseEntity<AuthPriorityDTO> findPriorityById(@PathVariable Long accountId, @PathVariable(name = "id") Long id);

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @DeleteMapping("{accountId}/prioritys/{id}")
    ResponseEntity<Void> deletePriorityById(@PathVariable Long accountId, @PathVariable(name = "id") Long id);
}
