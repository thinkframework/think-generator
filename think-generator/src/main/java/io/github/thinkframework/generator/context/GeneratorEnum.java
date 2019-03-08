package io.github.thinkframework.generator.context;

/**
 * 枚举.
 * @author lixiaobin
 * @since 2017/3/24
 */
public enum GeneratorEnum {
    DEFAULT_APPLICATION_CONTEXT_PROPERTIES("think-generator.properties");

    private final String value;

    public final String value() {
        return value;
    }

    GeneratorEnum(String value){
        this.value = value;
    }
}
