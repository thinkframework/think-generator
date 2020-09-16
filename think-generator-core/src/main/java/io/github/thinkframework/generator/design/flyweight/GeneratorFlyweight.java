package io.github.thinkframework.generator.design.flyweight;

import java.util.function.Function;

public class GeneratorFlyweight {

    public Object test(Object a, Function b){
        return b.apply(a);
    }
}
