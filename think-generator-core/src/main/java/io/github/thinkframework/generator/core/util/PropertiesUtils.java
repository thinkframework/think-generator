package io.github.thinkframework.generator.core.util;

import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 数据库类型映射
 * 做一些适当的修改
 *
 * @author lixiaobin
 */

public class PropertiesUtils {
    public static final String CLASS_PATH = "classpath*://";
    public static final String FILE = "file://";

    public static InputStream getInputStream(String configLocation) throws GeneratorRuntimeException {
        if (configLocation.startsWith(CLASS_PATH)) {
            return GeneratorProperties.class.getClassLoader().getResourceAsStream(configLocation.substring(CLASS_PATH.length()));
        } else if (configLocation.startsWith(FILE)) {
            try {
                return new FileInputStream(configLocation.substring(FILE.length()));
            } catch (FileNotFoundException e) {
                throw new GeneratorRuntimeException("文件未找到", e);
            }
        }
        return null;
    }
}
