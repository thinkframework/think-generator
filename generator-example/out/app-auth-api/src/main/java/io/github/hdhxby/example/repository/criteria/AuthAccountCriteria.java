package io.github.hdhxby.example.repository.criteria;

import io.github.thinkframework.commons.repository.criteria.AbstractCriteria;
import io.github.thinkframework.commons.repository.filter.Filter;
import io.github.thinkframework.commons.repository.filter.InstantFilter;
import io.github.thinkframework.commons.repository.filter.LongFilter;
import io.github.thinkframework.commons.repository.filter.StringFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限查询条件.
 * 这个类用于AuthAccountResource从Http GET请求参数接收所有可能的过滤选项.
 * 例如以下可能是有效的请求:
 * <code> /authAccounts?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false </code>
 * 由于Spring无法正确转换类型，除非使用特定的{@link Filter}类,否则我们需要使用修复类型特定的过滤器。
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限条件")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class AuthAccountCriteria extends AbstractCriteria {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，英文，默认是姓名的小写拼音
     *
     */
    private StringFilter username;

    /**
     * 账号的密码
     *
     */
    private StringFilter password;

    /**
     * 员工姓名，中文
     *
     */
    private StringFilter name;

    /**
     * 账号的说明备注
     *
     */
    private StringFilter remark;
}
