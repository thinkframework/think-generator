package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * 生成器对象
 * @author lixiaobin
 * @since 1.0.0
 */
public class Generator implements BeanFactoryAware {

    private final static Logger logger = LoggerFactory.getLogger(Generator.class);

    private GeneratorConfiguration generatorConfiguration;

    /**
     * 生成
     * @return
     */
    public void generate() throws GeneratorRuntimeException {
        try {
            Assert.notNull(generatorConfiguration,"配置文件不存在");
            logger.info("传入的表名称:{}",GeneratorContext.get().getTableName());
            GeneratorProperties generatorProperties = new GeneratorProperties(generatorConfiguration);

            new TableFactory(GeneratorContext.get().getBeanFactory().getBean(GeneratorContext.get().getDastSourceName(),DataSource.class))
                .getTables(GeneratorContext.get().getTableName())//获取表,模糊查询
                .stream().map(TableImpl::getTableName)//获取表名称
//                .parallel()//并行执行
                .forEach(tableName ->{
                        //设置环境变量
                        GeneratorProperties generatorPropertiesClone = generatorProperties.clone();
                        generatorPropertiesClone.setProperty("tableName", tableName);
                        //调用所有的提供者
                        GeneratorContext.get().getBeanFactory().getBeanProvider(GeneratorProvider.class)
                            .forEach(generatorProvider -> generatorProvider.build(generatorPropertiesClone));//完善数据
                        //模板路径必须存在

                        //初始化freemaker,不确定freemaker多线程环境下的运行情况
                        GeneratorFreeMarker generatorFreeMarker = new GeneratorFreeMarker();
                        generatorFreeMarker.setTemplateLoader(generatorConfiguration);

                        generatorFreeMarker.process(generatorPropertiesClone.getProperties());
                });
        } catch (GeneratorRuntimeException e) {
            logger.error("生成器异常.",e);
            //TODO 异常转换掉
            throw new GeneratorRuntimeException(e);
        }
    }

    /**
     * 设置BeanFactory.
     * 从头实现一个ApplicationContext太麻烦....
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        GeneratorContext.get().setBeanFactory(beanFactory);
    }


    public Generator beanFactory(BeanFactory beanFactory) throws BeansException {
        GeneratorContext.get().setBeanFactory(beanFactory);
        return this;
    }


    public Generator generatorConfiguration(GeneratorConfiguration generatorConfiguration) throws BeansException {
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public Generator dataSourceName(String dataSourceName) {
        GeneratorContext.get().setDastSourceName(dataSourceName);
        return this;
    }

    public Generator tableName(String tableName) {
        GeneratorContext.get().setTableName(tableName);
        return this;
    }
}
