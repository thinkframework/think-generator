package com.hdhxby.ecommerce.auth.service.dto;

import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限数据传输对象原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthAccountDTOPrototype extends AbstractAuditingPrototype {

    public static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    public static final String UPDATED_USERNAME = "BBBBBBBBBB";

    public static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    public static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    public static final String DEFAULT_NAME = "AAAAAAAAAA";
    public static final String UPDATED_NAME = "BBBBBBBBBB";

    public static final String DEFAULT_REMARK = "AAAAAAAAAA";
    public static final String UPDATED_REMARK = "BBBBBBBBBB";

    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountDTO create() {
        return AuthAccountDTO.builder()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .name(DEFAULT_NAME)
            .remark(DEFAULT_REMARK)
            .build();
    }


    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountDTO update(AuthAccountDTO authAccount) {
        return authAccount.toBuilder()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .build();
    }
}
