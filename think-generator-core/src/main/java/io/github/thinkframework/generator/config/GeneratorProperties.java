package io.github.thinkframework.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * 配置文件解析.
 * 基于xpath
 * 实现原型设计模式
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Data
@ConfigurationProperties("think.generator")
public class GeneratorProperties {

    private StragegyConfiguration stragegy;


    private Boolean enabled;

    private GeneratorConfiguration configuration = new GeneratorConfiguration();

    @Data
    public static class GeneratorConfiguration {
        private String frameName;
        private String companyName;
        private String appName;
        private String moduleName;
        private String authorName;
        private String namespace;
        private String template;
        private List<String> extensions;
        private Map<String, String> converts;
        private List<String> prefixs;
        private List<String> ignores;
        private String output;
    }

    @Data
    public static class StragegyConfiguration {
        private String clazz;
        private List<String> providers;
    }
}
