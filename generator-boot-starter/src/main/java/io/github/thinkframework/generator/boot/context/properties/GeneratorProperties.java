package io.github.thinkframework.generator.boot.context.properties;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置文件解析.
 * 基于xpath
 * 实现原型设计模式
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
@ConfigurationProperties("think.generator")
public class GeneratorProperties {

    private GeneratorConfiguration configuration = new GeneratorConfiguration();

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

}
