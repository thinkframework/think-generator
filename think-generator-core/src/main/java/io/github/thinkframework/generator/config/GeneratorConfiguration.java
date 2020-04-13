package io.github.thinkframework.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Accessors(chain = true)
@Data
@ConfigurationProperties("think.generator.configuration")
public class GeneratorConfiguration {
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
