package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.util.BeanUtils;
import io.github.thinkframework.generator.core.util.StringUtils;

import java.io.File;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * 解析配置文件
 * 默认
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class ConfigurationResponsibility<S,T> implements GeneratorResponsibility<S,T> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator,GeneratorContext generatorContext,S source,T target) {
        BeanUtils.describe(generatorContext.getGeneratorConfiguration()).forEach((key, value) -> {
            //设置GeneratorConfiguration属性
            generatorContext.getProperties().put(key, value);
            if (value instanceof String) {//转换成路径
                generatorContext.getProperties().put(key+"_"+"path", value.toString().replace(".", Matcher.quoteReplacement(File.separator)));
            }
        });

        Optional.ofNullable(target).ifPresent(temp -> {
            String name = temp.toString();
            generatorContext.getProperties().put("tableName", StringUtils.lowerUnderScore(name));
            generatorContext.getProperties().put("className", StringUtils.upperCamel(name));
            generatorContext.getProperties().put("className_lowerHyphen", StringUtils.lowerHyphen(StringUtils.lowerCamel(name)));
        });
    }

}
