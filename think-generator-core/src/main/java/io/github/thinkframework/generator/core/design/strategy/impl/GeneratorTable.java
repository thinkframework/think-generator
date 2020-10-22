package io.github.thinkframework.generator.core.design.strategy.impl;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.flyweight.GeneratorFlyweight;
import io.github.thinkframework.generator.core.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.core.design.templatemethod.AbstractStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.sql.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

import javax.sql.DataSource;

/**
 * 根据表生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorTable extends AbstractStrategy<DataSource,String> {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GeneratorClass.class);

    /**
     * 生成
     *
     * @return
     */
    @Override
    public void internal(GeneratorContext<DataSource,String> generatorContext) throws GeneratorRuntimeException {
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
            .forEach(super::responsibilitys);
    }
}
