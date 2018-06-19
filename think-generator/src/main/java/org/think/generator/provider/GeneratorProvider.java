package org.think.generator.provider;

import java.util.Map;

/**
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface GeneratorProvider {
    Map build(Map map);
}
