package io.github.thinkframework.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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

    private String stragegy;

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
        private List<String> providers;
        private Map<String, String> converts;
        private List<String> prefixs;
        private List<String> ignores;
        private String output;
    }
}
