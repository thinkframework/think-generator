package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface Generator<S,T> {

    void generate() throws GeneratorRuntimeException;

    Generator generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    Generator generatorProviders(List<GeneratorProvider> generatorProviders);

    Generator dataSource(S dataSource);

    Generator tableName(T tableName);

}
