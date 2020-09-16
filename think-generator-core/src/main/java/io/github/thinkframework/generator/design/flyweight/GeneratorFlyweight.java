package io.github.thinkframework.generator.design.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 缓存实现
 * @author hdhxby
 */
public class GeneratorFlyweight {
    private static Map<Function,Map> map = new HashMap();
    public static <S,T> T get(S s, Function<S,T> f){
        return f.apply(s);
//        return (T) map.computeIfAbsent(f, o -> new HashMap<S,T>())
//        .computeIfAbsent(s,f);
    }
}
