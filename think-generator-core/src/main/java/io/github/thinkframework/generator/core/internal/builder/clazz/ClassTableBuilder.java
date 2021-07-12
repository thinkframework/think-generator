package io.github.thinkframework.generator.core.internal.builder.clazz;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

/**
 * 通过表生成对应的类
 *
 * @author hdhxby
 */
public class ClassTableBuilder {

    private GeneratorConfiguration generatorConfiguration;

    public ClassTableBuilder generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public Table build(Clazz clazz) {
        throw new GeneratorRuntimeException("暂不支持");
    }
}
