package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.BeanUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import org.springframework.core.Ordered;

import java.io.File;
import java.sql.Types;
import java.util.regex.Matcher;

/**
 * 适配器
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ConfigurationGeneratorProvider implements GeneratorProvider, Ordered {

    @Override
    public GeneratorContext build(GeneratorContext generatorContext) {
        generatorContext.getGeneratorConfiguration().getConverts().forEach((key,value) -> {
            if(key.startsWith("java.sql.Types.")) {
                try {
                    TypesUtils.put(Types.class.getField(key.substring("java.sql.Types.".length())).getInt(Types.class),Class.forName(value));
                } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                    throw new GeneratorRuntimeException(e);
                }
            }
        });

        BeanUtils.describe(generatorContext.getGeneratorConfiguration()).forEach((key, value) -> {
            //设置GeneratorConfiguration属性
            generatorContext.getProperties().put(key, value);
            if (value instanceof String) {//转换成路径
                generatorContext.getProperties().put(key+"_"+"path", value.toString().replace(".", Matcher.quoteReplacement(File.separator)));
            }
        });
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
