package ${packageName}.service.assembler;

import ${packageName}.domain.entity.${className};
import ${packageName}.domain.vo.${className}VO;
import ${packageName}.api.dto.${className}DTO;
import io.github.thinkframework.commons.mapper.Mapper;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限实体和数据传输对象映射.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface ${className}Assembler extends Mapper<${className}, ${className}DTO> {

    @Override
    ${className} toEntity(${className}DTO dto);

    @Override
    ${className}DTO toDto(${className} entity);

    @Override
    ${className} toEntity(${className}VO dto);

    @Override
    ${className}DTO toVo(${className} entity);

}
