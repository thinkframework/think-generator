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

    private static ThreadLocal<GeneratorContext> context = new ThreadLocal<>();

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext get() {
        GeneratorContext generatorContext = context.get();
        if (generatorContext == null) {
            context.set(new GeneratorContext());
        }
        return context.get();
    }


    public static GeneratorContext set(GeneratorProperties generatorProperties) {
        GeneratorContext generatorContext = context.get();
        if (generatorContext == null) {
            context.set(new GeneratorContext(generatorProperties));
        }
        return context.get();
    }

    public GeneratorContext() {

    }

    public GeneratorContext(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }


//    public void setProperties(Properties properties) {
//        generatorProperties = new GeneratorProperties();
//        generatorProperties.setProperties(properties);
//    }

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
