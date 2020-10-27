package io.github.thinkframework.generator.core.design.strategy.impl;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.flyweight.GeneratorFlyweight;
import io.github.thinkframework.generator.core.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.core.design.strategy.AbstractStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * 根据表生成
 *
 * @author hdhxby
 * @since 1.0.0
 */
public class GeneratorTable extends AbstractStrategy<DataSource,String> {
    private static final Logger log = LoggerFactory.getLogger(GeneratorClass.class);

    @Override
    public GeneratorContext<DataSource,String> process(GeneratorContext<DataSource, String> generatorContext) throws GeneratorRuntimeException {
        log.info("传入的表名称:{}", generatorContext.getTarget());
        GeneratorFlyweight.get(generatorContext.getTarget(), target -> new GeneratorDatabaseMetaData(generatorContext.getSource())
            .getTables(target))//获取表,模糊查询,已缓存
            .stream()//并行执行
            .map(Table::getTableName)//获取表名称
            .peek(tableName -> log.info("匹配的表名称:{}", tableName))
            .map(tableName ->
                GeneratorPrototype.clone(generatorContext)
                    .source(generatorContext.getSource())
                    .target(tableName))//设置环境上下文
            .forEach(ontext -> responsibilitys.forEach(responsibility -> responsibility.process(generatorContext)));
        return generatorContext;
    }


//    public void internal(GeneratorContext<DataSource,String> generatorContext) throws GeneratorRuntimeException { }

    @Override
    public Class<DataSource> source() {
        return DataSource.class;
    }
}
