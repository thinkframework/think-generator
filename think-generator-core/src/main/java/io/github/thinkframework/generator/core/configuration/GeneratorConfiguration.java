package io.github.thinkframework.generator.core.configuration;

import java.util.List;
import java.util.Map;

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

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public Map<String, String> getConverts() {
        return converts;
    }

    public void setConverts(Map<String, String> converts) {
        this.converts = converts;
    }

    public List<String> getPrefixs() {
        return prefixs;
    }

    public void setPrefixs(List<String> prefixs) {
        this.prefixs = prefixs;
    }

    public List<String> getIgnores() {
        return ignores;
    }

    public void setIgnores(List<String> ignores) {
        this.ignores = ignores;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
