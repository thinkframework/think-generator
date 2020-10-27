package io.github.thinkframework.generator.core.design.abstractfactory;

import java.io.File;
import java.util.Map;

public interface AbstractFactory {

    /**
     * 返回文件
     * @param map
     * @param file
     * @return
     */
    File file(Map map,File file);

    /**
     * 返回字符串
     * @param map
     * @param string
     * @return
     */
    String string(Map map,String string);
}
