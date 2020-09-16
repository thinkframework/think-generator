package io.github.thinkframework.generator.strategy;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.List;

/**
 * 根据数据库生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public class GeneratorTable implements GeneratorStrategy<DataSource,String> {

    private List<GeneratorProvider> providers;

    private GeneratorConfiguration configuration;

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
                //TODO 原型模式,有时间再说
                new GeneratorContext<>().generatorConfiguration(generatorContext.getGeneratorConfiguration())
                .source(generatorContext.getSource())
                .target(tableName))//设置环境上下文
            .peek(context -> providers.stream()
                .forEach(generatorProvider -> {
                    generatorProvider.build(context);
                }))//调用所有的提供者,填充数据
            .forEach(this::process);
    }

    @Override
    public GeneratorStrategy providers(List<GeneratorProvider> providers){
        this.providers = providers;
        return this;
    }

    @Override
    public GeneratorStrategy generatorConfiguration(GeneratorConfiguration configuration){
        this.configuration = configuration;
        return this;
    }

}
