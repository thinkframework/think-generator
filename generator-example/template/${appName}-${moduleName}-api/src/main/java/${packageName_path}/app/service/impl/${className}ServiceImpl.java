package ${packageName}.service.impl;

import ${packageName}.domain.${className};
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.repository.${className}Repository;
import ${packageName}.repository.criteria.${className}Criteria;
import ${packageName}.service.${className}Service;
import ${packageName}.service.dto.${className}DTO;
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
public class ${className}ServiceImpl extends AbstractService<${className}Repository, ${className}Mapper,
    ${className}, ${className}DTO, ${className}Criteria, Long> implements ${className}Service {

}
