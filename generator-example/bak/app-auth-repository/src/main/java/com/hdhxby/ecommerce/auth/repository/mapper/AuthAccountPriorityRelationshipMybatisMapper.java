package com.hdhxby.ecommerce.auth.repository.mapper;

import com.hdhxby.ecommerce.auth.domain.AuthAccountPriorityRelationship;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import io.github.thinkframework.commons.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号与权限的关系表，一个账号可以对应多个权限，一个权限也可以属于多个账号仓库Mapper实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper
public interface AuthAccountPriorityRelationshipMybatisMapper extends MybatisMapper<AuthAccountPriorityRelationship, AuthAccountPriorityRelationshipCriteria, Long> {

}
