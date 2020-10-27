package io.github.thinkframework.generator.core.util;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.design.proxy.Proxy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理TypesUtils
 * 静态方法改实例方法
 *
 * @author hdhxby
 */
public class TypesProxy implements Proxy {
    public static Map<Integer, Class> map = new HashMap<Integer, Class>();

    public TypesProxy(GeneratorConfiguration generatorConfiguration) {
        generatorConfiguration.getConverts().forEach((key,value) -> {
            if(key.startsWith("java.sql.Types.")) {
                try {
                    map.put(Types.class.getField(key.substring("java.sql.Types.".length())).getInt(Types.class),Class.forName(value));
                } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                    throw new GeneratorRuntimeException(e);
                }
            }
        });
    }

    public Class dataType(Integer dataType) {
        return map.compute(dataType,(kev,value) -> value = TypesMapping.getInstance().dataType(kev));
    }
}
