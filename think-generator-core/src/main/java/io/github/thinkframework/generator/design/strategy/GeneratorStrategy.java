package io.github.thinkframework.generator.design.strategy;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.design.chain.of.responsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void generate(GeneratorContext<S,T> generatorContext) throws GeneratorRuntimeException;

    GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys);

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

}
