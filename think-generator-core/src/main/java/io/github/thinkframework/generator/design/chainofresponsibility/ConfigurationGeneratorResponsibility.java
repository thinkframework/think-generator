package io.github.thinkframework.generator.design.chainofresponsibility;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.BeanUtils;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import org.springframework.core.Ordered;

import java.io.File;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * 适配器
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ConfigurationGeneratorResponsibility implements GeneratorResponsibility, Ordered {

    @Override
    public GeneratorContext process(GeneratorContext generatorContext) {
        BeanUtils.describe(generatorContext.getGeneratorConfiguration()).forEach((key, value) -> {
            //设置GeneratorConfiguration属性
            generatorContext.getProperties().put(key, value);
            if (value instanceof String) {//转换成路径
                generatorContext.getProperties().put(key+"_"+"path", value.toString().replace(".", Matcher.quoteReplacement(File.separator)));
            }
        });

        generatorContext.getGeneratorConfiguration().getConverts().forEach((key,value) -> {
            if(key.startsWith("java.sql.Types.")) {
                try {
                    TypesUtils.put(Types.class.getField(key.substring("java.sql.Types.".length())).getInt(Types.class),Class.forName(value));
                } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                    throw new GeneratorRuntimeException(e);
                }
            }
        });
        Optional.ofNullable(generatorContext.getTarget()).ifPresent(target -> {
            String name = target.toString();
            generatorContext.getProperties().put("tableName", StringUtils.underScoreCase(name));

            //根据下划线拆分
            List<String> prefixs = generatorContext.getGeneratorConfiguration().getPrefixs();
            for (String prefix : prefixs) {
                if (name.toUpperCase().startsWith(prefix)) {
                    name = name.toUpperCase().replaceFirst(prefix, "");
                    break;
                }
            }
            generatorContext.getProperties().put("className", StringUtils.camelCase(name));

        });
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
