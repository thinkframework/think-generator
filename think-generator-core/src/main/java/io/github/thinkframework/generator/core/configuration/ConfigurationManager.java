package io.github.thinkframework.generator.core.configuration;

/**
 * 单例配置管理器
 * @author lixiaobin
 */
public class ConfigurationManager {
    static volatile  ConfigurationManager instance;

    public static ConfigurationManager getInstance() {
        if(instance == null){
            synchronized (ConfigurationManager.class){
                if(instance != null){
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private ConfigurationManager() {

    }
}
