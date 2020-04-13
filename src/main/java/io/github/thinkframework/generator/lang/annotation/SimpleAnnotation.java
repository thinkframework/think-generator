package io.github.thinkframework.generator.lang.annotation;

/**
 * ClazzAnnotation接口的简单实现
 *
 * @author lixiaobin
 * @see ClazzAnnotation
 * @since 2017/3/24
 */
public class SimpleAnnotation implements ClazzAnnotation {
    private String name;

    public SimpleAnnotation(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
