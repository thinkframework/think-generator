package io.github.thinkframework.generator.design.strategy.impl;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.design.flyweight.GeneratorFlyweight;
import io.github.thinkframework.generator.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.design.templatemethod.AbstractStrategy;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.internal.sql.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.internal.sql.databasemetadata.Table;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

/**
 * 根据数据库生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public class GeneratorTable extends AbstractStrategy<DataSource,String> {

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
