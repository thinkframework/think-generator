package com.hdhxby.ecommerce.auth.service.dto;

import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 账号和角色的关系表，一个账号可以对应多个角色，一个角色也可以对应多个账号数据传输对象原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthAccountRoleRelationshipDTOPrototype extends AbstractAuditingPrototype {

    public static final Long DEFAULT_ACCOUNT_ID = 1L;
    public static final Long UPDATED_ACCOUNT_ID = 2L;

    public static final Long DEFAULT_ROLE_ID = 1L;
    public static final Long UPDATED_ROLE_ID = 2L;

    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountRoleRelationshipDTO create() {
        return AuthAccountRoleRelationshipDTO.builder()
            .accountId(DEFAULT_ACCOUNT_ID)
            .roleId(DEFAULT_ROLE_ID)
            .build();
    }


    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountRoleRelationshipDTO update(AuthAccountRoleRelationshipDTO authAccountRoleRelationship) {
        return authAccountRoleRelationship.toBuilder()
            .accountId(UPDATED_ACCOUNT_ID)
            .roleId(UPDATED_ROLE_ID)
            .build();
    }
}
