package io.github.thinkframework.generator.design.strategy.impl;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.design.templatemethod.AbstractStrategy;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
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
    public void generate(GeneratorContext<DataSource,String> generatorContext) throws GeneratorRuntimeException {
//        Assert.notNull(configuration, "配置文件不存在");

        log.info("传入的表名称:{}", generatorContext.getTarget());

        new TableFactory(generatorContext.getSource())
            .getTables(generatorContext.getTarget())//获取表,模糊查询
            .stream()//并行执行
            .map(TableImpl::getTableName)//获取表名称
            .map(tableName ->
                GeneratorPrototype.clone(generatorContext)
                .source(generatorContext.getSource())
                .target(tableName))//设置环境上下文
            .peek(context -> responsibilitys.stream()
                .forEach(generatorProvider -> {
                    generatorProvider.process(context);
                }))//调用所有的提供者,填充数据
            .forEach(this::process);
    }
}
