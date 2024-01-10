package com.hdhxby.ecommerce.auth.repository.mapper;

import com.hdhxby.ecommerce.auth.domain.AuthPriority;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthPriorityCriteria;
import io.github.thinkframework.commons.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限表，每个权限代表了系统中的一个菜单、按钮、URL请求仓库Mapper实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper
public interface AuthPriorityMybatisMapper extends MybatisMapper<AuthPriority, AuthPriorityCriteria, Long> {

}
