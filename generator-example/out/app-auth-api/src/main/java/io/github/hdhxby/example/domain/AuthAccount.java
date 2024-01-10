package io.github.hdhxby.example.domain.entity;

import io.github.thinkframework.common.api.Entity;
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
public class AuthAccount extends Entity {

        /**
         * 用户名，英文，默认是姓名的小写拼音
         *
         */
        private String username;

        /**
         * 账号的密码
         *
         */
        private String password;

        /**
         * 员工姓名，中文
         *
         */
        private String name;

        /**
         * 账号的说明备注
         *
         */
        private String remark;

}
