package com.kcallme.file.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限
 *
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class AuthAccountDTO {

    
    private Long id;

    
    private String username;

    
    private String password;

    
    private String name;

    
    private String remark;

    
    private Long createdBy;

    
    private Instant createdDate;

    
    private Long lastModifiedBy;

    
    private Instant lastModifiedDate;

}
