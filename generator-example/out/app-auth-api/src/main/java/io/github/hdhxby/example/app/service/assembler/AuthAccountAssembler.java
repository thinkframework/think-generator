package io.github.hdhxby.example.service.assembler;

import io.github.hdhxby.example.domain.entity.AuthAccount;
import io.github.hdhxby.example.domain.vo.AuthAccountVO;
import io.github.hdhxby.example.api.dto.AuthAccountDTO;
import io.github.thinkframework.commons.mapper.Mapper;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限实体和数据传输对象映射.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface AuthAccountAssembler extends Mapper<AuthAccount, AuthAccountDTO> {

    @Override
    AuthAccount toEntity(AuthAccountDTO dto);

    @Override
    AuthAccountDTO toDto(AuthAccount entity);

    @Override
    AuthAccount toEntity(AuthAccountVO dto);

    @Override
    AuthAccountDTO toVo(AuthAccount entity);

}
