package io.github.thinkframework.apt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 链式编程
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Chain {
    boolean value() default true;
}
