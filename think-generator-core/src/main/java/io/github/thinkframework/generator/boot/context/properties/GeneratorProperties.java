package io.github.thinkframework.generator.boot.context.properties;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * 配置文件解析.
 * 基于xpath
 * 实现原型设计模式
 *
 * @author hdhxby
 * @since 1.0.0
 */
@ConfigurationProperties("think.generator")
public class GeneratorProperties {

    private StragegyConfiguration stragegy;


    private Boolean enabled;

    private GeneratorConfiguration configuration = new GeneratorConfiguration();

    public StragegyConfiguration getStragegy() {
        return stragegy;
    }

    public void setStragegy(StragegyConfiguration stragegy) {
        this.stragegy = stragegy;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }


    public static class StragegyConfiguration {
        private String clazz;
        private List<String> responsibilitys;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public List<String> getResponsibilitys() {
            return responsibilitys;
        }

        public void setResponsibilitys(List<String> responsibilitys) {
            this.responsibilitys = responsibilitys;
        }
    }
}
