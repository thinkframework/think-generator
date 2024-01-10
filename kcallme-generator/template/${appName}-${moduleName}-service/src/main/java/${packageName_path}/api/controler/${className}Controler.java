package ${packageName}.api.controller;

import ${packageName}.repository.criteria.${className}Criteria;
import ${packageName}.service.dto.${className}DTO;
import ${packageName}.service.dto.AuthPriorityDTO;
import ${packageName}.service.dto.AuthRoleDTO;
import io.github.thinkframework.commons.web.rest.CriteriaResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${clazz.remarks}.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
@RequestMapping("/api/auth/auth-accounts")
public interface  ${className}Controler extends CriteriaResource<${className}DTO, ${className}Criteria, Long> {

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
