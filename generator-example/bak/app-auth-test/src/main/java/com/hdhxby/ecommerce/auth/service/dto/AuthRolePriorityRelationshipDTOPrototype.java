package com.hdhxby.ecommerce.auth.service.dto;

import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 角色和权限的关系表，角色和权限是多对多的关系，一个角色可以对应多个权限，一个权限可以属于多个角色数据传输对象原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthRolePriorityRelationshipDTOPrototype extends AbstractAuditingPrototype {

    public static final Long DEFAULT_PRIORITY_ID = 1L;
    public static final Long UPDATED_PRIORITY_ID = 2L;

    public static final Long DEFAULT_ROLE_ID = 1L;
    public static final Long UPDATED_ROLE_ID = 2L;

    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthRolePriorityRelationshipDTO create() {
        return AuthRolePriorityRelationshipDTO.builder()
            .priorityId(DEFAULT_PRIORITY_ID)
            .roleId(DEFAULT_ROLE_ID)
            .build();
    }


    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthRolePriorityRelationshipDTO update(AuthRolePriorityRelationshipDTO authRolePriorityRelationship) {
        return authRolePriorityRelationship.toBuilder()
            .priorityId(UPDATED_PRIORITY_ID)
            .roleId(UPDATED_ROLE_ID)
            .build();
    }
}
