package com.hdhxby.ecommerce.auth.service.dto;

import io.github.thinkframework.commons.prototype.AbstractAuditingPrototype;

/**
 * 权限表，每个权限代表了系统中的一个菜单、按钮、URL请求数据传输对象原型.
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
public class AuthPriorityDTOPrototype extends AbstractAuditingPrototype {

    public static final String DEFAULT_CODE = "AAAAAAAAAA";
    public static final String UPDATED_CODE = "BBBBBBBBBB";

    public static final String DEFAULT_URL = "AAAAAAAAAA";
    public static final String UPDATED_URL = "BBBBBBBBBB";

    public static final String DEFAULT_PRIORITY_COMMENT = "AAAAAAAAAA";
    public static final String UPDATED_PRIORITY_COMMENT = "BBBBBBBBBB";

    public static final Integer DEFAULT_PRIORITY_TYPE = 0;
    public static final Integer UPDATED_PRIORITY_TYPE = 1;

    public static final Long DEFAULT_PARENT_ID = 1L;
    public static final Long UPDATED_PARENT_ID = 2L;

    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthPriorityDTO create() {
        return AuthPriorityDTO.builder()
            .code(DEFAULT_CODE)
            .url(DEFAULT_URL)
            .priorityComment(DEFAULT_PRIORITY_COMMENT)
            .priorityType(DEFAULT_PRIORITY_TYPE)
            .parentId(DEFAULT_PARENT_ID)
            .build();
    }


    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthPriorityDTO update(AuthPriorityDTO authPriority) {
        return authPriority.toBuilder()
            .code(UPDATED_CODE)
            .url(UPDATED_URL)
            .priorityComment(UPDATED_PRIORITY_COMMENT)
            .priorityType(UPDATED_PRIORITY_TYPE)
            .parentId(UPDATED_PARENT_ID)
            .build();
    }
}
