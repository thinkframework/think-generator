package io.github.thinkframework.generator.core.internal.lang.annotation;

/**
 * ClazzAnnotation接口的简单实现
 *
 * @author hdhxby
 * @see ClazzAnnotation
 * @since 2017/3/24
 */
public class SimpleAnnotation implements ClazzAnnotation {
    private String name;

    public SimpleAnnotation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
