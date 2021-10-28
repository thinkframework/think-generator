package io.github.thinkframework.generator.core.strategy.impl;

import io.github.thinkframework.generator.core.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.core.strategy.AbstractStrategy;

import javax.sql.DataSource;
import java.util.Iterator;

/**
 * 根据表生成
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public class GeneratorTable extends AbstractStrategy<DataSource,String> {

    /**
     * @param generatorContext 线程安全的上下文
     * @param source
     * @param target
     * @throws GeneratorRuntimeException
     */
    @Override
    public void execute(GeneratorContext<DataSource, String> generatorContext,DataSource source,String target) throws GeneratorRuntimeException {
        GeneratorDatabaseMetaData.get(source)
            .getTables(target)//获取表,模糊查询,已缓存
            .stream()//并行执行
            .map(Table::getTableName)//获取表名称
            .peek(tableName -> log.info("匹配的表名称:{}", tableName))
//            .map(tableName -> generatorContext.clone())//设置环境上下文
            .forEach(tableName -> {
                Iterator<GeneratorResponsibility> iterator = responsibilitys.iterator();
                if(iterator.hasNext()) {
                    iterator.next().execute(iterator, GeneratorContext.get().generatorConfiguration(generatorContext.getGeneratorConfiguration()),
                        source, tableName);
                }
            });
    }

    @Override
    public Class<DataSource> source() {
        return DataSource.class;
    }
}
