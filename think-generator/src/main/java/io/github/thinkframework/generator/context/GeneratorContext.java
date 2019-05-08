package io.github.thinkframework.generator.context;

import org.springframework.beans.factory.BeanFactory;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext {
    private GeneratorProperties generatorProperties;

    private BeanFactory beanFactory;

    private String dastSourceName;

    private String tableName;

    private static ThreadLocal<GeneratorContext> context =  ThreadLocal.withInitial(() ->{
        GeneratorContext generatorContext = new GeneratorContext();
        //设置默认数据源,支持多数据源场景
        generatorContext.setDastSourceName(GeneratorEnum.DEFAULT_DATA_SOURCE_NAME.value());
        return generatorContext;
    });

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext get() {
        return context.get();
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getDastSourceName() {
        return dastSourceName;
    }

    public void setDastSourceName(String dastSourceName) {
        this.dastSourceName = dastSourceName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public <T> T getBean(String name,Class<T> requiredType){
        return beanFactory.getBean(name,requiredType);
    }
}
