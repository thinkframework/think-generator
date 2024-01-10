package ${packageName}.domain.entity;

import ${frameName}.common.api.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ${clazz.remarks}
 *
 * @author ${authorName}
 * @version 1.0.0
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class ${className} extends Entity {
<#list clazz.fields as field>
    <#if !field.ignore>

        /**
         * ${field.remarks}
         *
         */
        <#t>${field.annotations}
        private ${field.type} ${field.name};
    </#if>
</#list>

}
