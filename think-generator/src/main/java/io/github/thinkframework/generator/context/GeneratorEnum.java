package io.github.thinkframework.generator.context;

/**
 * 枚举.
 * @author lixiaobin
 * @since 2017/3/24
 */
public enum GeneratorEnum {
    DEFAULT_APPLICATION_CONTEXT_XML("applicationContext.xml"),
    DEFAULT_APPLICATION_CONTEXT_PROPERTIES("generator.properties"),

    /**
     * 默认数据源,支持多数据源的场景
     */
    DEFAULT_DATA_SOURCE_NAME("dataSource");

    private final String value;

    public final String value() {
        return value;
    }

    GeneratorEnum(String value){
        this.value = value;
    }
}
