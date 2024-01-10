package com.hdhxby.ecommerce.auth.service.domain;

import com.hdhxby.ecommerce.auth.domain.AuthRole;
import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限实体原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthRolePrototype extends AbstractAuditingPrototype {

    public static final String DEFAULT_CODE = "AAAAAAAAAA";
    public static final String UPDATED_CODE = "BBBBBBBBBB";

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
    public static AuthRole create() {
        return AuthRole.builder()
            .code(DEFAULT_CODE)
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
    public static AuthRole update(AuthRole authRole) {
        return authRole.toBuilder()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .build();
    }
}
