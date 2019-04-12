package io.github.thinkframework.generator;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.FreeMarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成器对象
 * @author lixiaobin
 * @since 1.0.0
 */
public class Generator implements BeanFactoryAware {

    private final static Logger logger = LoggerFactory.getLogger(GeneratorApplication.class);

    private BeanFactory beanFactory;

    private GeneratorProperties generatorProperties;

    public Generator(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Generator(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }

    /**
     * 生成
     * @return
     */
    public Generator generate() throws GeneratorRuntimeException {
        try {
            logger.info("传入的表名称:{}",this.generatorProperties.getTableName());
            //设置环境变量
//            GeneratorContext.set(this.generatorProperties);

            GeneratorProperties generatorProperties = this.generatorProperties.clone();
            new TableFactory(this.generatorProperties.getDataSource())
                .getTables(this.generatorProperties.getTableName())//获取表
                .stream().map(TableImpl::getTableName)
                .forEach(tableName ->{
                    GeneratorContext.set(generatorProperties);
                    generatorProperties.setTableName(tableName);
                    generatorProperties.setProperty("tableName",tableName);
                    //调用所有的提供者
                    getGeneratorProviders().forEach(generatorProvider ->{
                        //完善数据
                        generatorProperties.putAll(generatorProvider.build(generatorProperties.getProperties()));
                    });
                    //输出
                    new FreeMarkerUtils(generatorProperties.getProperties()).process();
                });
        } catch (Exception e) {
            logger.error("",e);
            throw new GeneratorRuntimeException(e);
        } finally {
            return this;
        }
    }

    /**
     * 获取所有提供者
     * @return
     */
    private List<GeneratorProvider> getGeneratorProviders() {
        return new ArrayList(){
            {
                add(new TableGeneratorProvider());
            }
        };
    }

    /**
     * 设置BeanFactory.
     * 从头实现一个ApplicationContext太麻烦....
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
