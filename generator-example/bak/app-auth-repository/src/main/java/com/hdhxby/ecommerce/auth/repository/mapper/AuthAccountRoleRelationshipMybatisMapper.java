package com.hdhxby.ecommerce.auth.repository.mapper;

import com.hdhxby.ecommerce.auth.domain.AuthAccountRoleRelationship;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountRoleRelationshipCriteria;
import io.github.thinkframework.commons.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号和角色的关系表，一个账号可以对应多个角色，一个角色也可以对应多个账号仓库Mapper实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper
public interface AuthAccountRoleRelationshipMybatisMapper extends MybatisMapper<AuthAccountRoleRelationship, AuthAccountRoleRelationshipCriteria, Long> {

}
