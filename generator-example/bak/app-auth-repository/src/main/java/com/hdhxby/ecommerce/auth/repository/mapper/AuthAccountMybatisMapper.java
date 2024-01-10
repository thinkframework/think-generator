package com.hdhxby.ecommerce.auth.repository.mapper;

import com.hdhxby.ecommerce.auth.domain.AuthAccount;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountCriteria;
import io.github.thinkframework.commons.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限仓库Mapper实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper
public interface AuthAccountMybatisMapper extends MybatisMapper<AuthAccount, AuthAccountCriteria, Long> {

    @Select(" select * " +
        " from ")
    Long countCode(@Param("accountId") Long accountId);


    @Select("" +
        "")
    Long countUrl(@Param("accountId") Long accountId);

}
