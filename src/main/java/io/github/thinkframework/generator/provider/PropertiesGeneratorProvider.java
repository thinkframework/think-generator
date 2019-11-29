package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 提供属性
 * @author lixiaobin
 * @since 2017/3/24
 */
public class PropertiesGeneratorProvider implements GeneratorProvider{

    @Override
    public GeneratorProperties build(GeneratorProperties generatorProperties){
        initProperties(generatorProperties.getProperties());
        return generatorProperties;
    }

    /**
     * 扩展属性
     * @return
     */
    protected Properties initProperties(Properties properties) {
        Iterator iterator = System.getProperties().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            properties.put("env_" + ((String) entry.getKey()).replaceAll("\\.", "_"), entry.getValue());
        }

        properties.put("now_yyyyMMddHHmmss", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        Properties path = new Properties();
        properties.forEach((key,value)->{

            path.put(key.toString().replace("generator.", ""), value);
            String key_path = key.toString().replace("generator.", "") + "_path";
            String value_path = value.toString().replace(".", "/");
            path.put(key_path, value_path);
        });
        properties.putAll(path);

        return properties;
    }

}
