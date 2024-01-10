package com.hdhxby.ecommerce.auth.repository.mapper;

import com.hdhxby.ecommerce.auth.domain.AuthRole;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRoleCriteria;
import io.github.thinkframework.commons.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限仓库Mapper实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper
public interface AuthRoleMybatisMapper extends MybatisMapper<AuthRole, AuthRoleCriteria, Long> {

}
