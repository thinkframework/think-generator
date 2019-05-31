package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Types;
import java.util.Map;
import java.util.Optional;

/**
 * 生成器对象
 * @author lixiaobin
 * @since 1.0.0
 */
public class Generator implements BeanFactoryAware, ResourceLoaderAware {

    private final static Logger logger = LoggerFactory.getLogger(Generator.class);

    private ResourceLoader resourceLoader;

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

            //覆盖数据库类型
            generatorConfiguration.getConverts().forEach((key, value) -> {
                try {
                    TypesUtils.put(Types.class.getField((key).replace("java.sql.Types.",""))
                            .getInt(Types.class),
                        Class.forName(value));
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                    throw new GeneratorRuntimeException("反射异常", e);
                }
            });

            new TableFactory(GeneratorContext.get().getBeanFactory().getBean(GeneratorContext.get().getDastSourceName(),DataSource.class))
                .getTables(GeneratorContext.get().getTableName())//获取表,模糊查询
                .stream().map(TableImpl::getTableName)//获取表名称
//                .parallel()//并行执行
                .forEach(tableName ->{
                        //设置环境变量
                    try {
                        GeneratorProperties generatorPropertiesClone =  generatorProperties.clone();
                        generatorPropertiesClone.setProperty("tableName", tableName);

                        //调用所有的提供者
                        GeneratorContext.get().getBeanFactory().getBeanProvider(GeneratorProvider.class)
                            .orderedStream()//需要保证顺序
                            .forEach(generatorProvider -> generatorProvider.build(generatorPropertiesClone));//完善数据

                        if(StringUtils.isNotEmpty(generatorConfiguration.getTemplate())) {//模板目录必须存在
                            FileUtils.listFiles(new File(generatorConfiguration.getTemplate()), generatorConfiguration.getExtensions().stream().toArray(String[]::new), true)
                                .forEach(input -> {
                                    GeneratorFreeMarker generatorFreeMarker = new GeneratorFreeMarker();
                                    //输出文件路径
                                    File file = new File(generatorFreeMarker.process(generatorPropertiesClone.getProperties(),
                                        input.getPath().replace(generatorConfiguration.getTemplate(),generatorConfiguration.getOutput()),
                                        null));
                                    //输出文件
                                    generatorFreeMarker.process(generatorPropertiesClone.getProperties(),input,file);
                                });
                        }
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
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

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
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
