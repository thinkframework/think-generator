package ${packageName}.service.impl;

import ${packageName}.domain.AuthRole;
import ${packageName}.mapper.AuthRoleMapper;
import ${packageName}.repository.AuthRoleRepository;
import ${packageName}.repository.criteria.AuthRoleCriteria;
import ${packageName}.service.AuthRoleService;
import ${packageName}.service.dto.AuthRoleDTO;
import io.github.thinkframework.commons.service.AbstractService;
import org.springframework.stereotype.Service;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限服务实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ${className}ServiceImpl extends AbstractService<${className}Repository, ${className}Mapper,
    ${className}, ${className}, ${className}Criteria, Long> implements AuthRoleService {

}
