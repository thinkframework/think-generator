package com.hdhxby.ecommerce.auth.service.domain;

import com.hdhxby.ecommerce.auth.domain.AuthAccountPriorityRelationship;
import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 账号与权限的关系表，一个账号可以对应多个权限，一个权限也可以属于多个账号实体原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthAccountPriorityRelationshipPrototype extends AbstractAuditingPrototype {

    public static final Long DEFAULT_ACCOUNT_ID = 1L;
    public static final Long UPDATED_ACCOUNT_ID = 2L;

    public static final Long DEFAULT_PRIORITY_ID = 1L;
    public static final Long UPDATED_PRIORITY_ID = 2L;

    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountPriorityRelationship create() {
        return AuthAccountPriorityRelationship.builder()
            .accountId(DEFAULT_ACCOUNT_ID)
            .priorityId(DEFAULT_PRIORITY_ID)
            .build();
    }

    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccountPriorityRelationship update(AuthAccountPriorityRelationship authAccountPriorityRelationship) {
        return authAccountPriorityRelationship.toBuilder()
            .accountId(UPDATED_ACCOUNT_ID)
            .priorityId(UPDATED_PRIORITY_ID)
            .build();
    }
}
