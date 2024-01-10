package ${packageName}.service;

import ${packageName}.repository.criteria.${className}Criteria;
import ${packageName}.service.dto.${className}DTO;
import io.github.thinkframework.commons.service.CriteriaService;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限服务实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ${className}Service extends CriteriaService<${className}DTO, ${className}Criteria, Long> {
}
