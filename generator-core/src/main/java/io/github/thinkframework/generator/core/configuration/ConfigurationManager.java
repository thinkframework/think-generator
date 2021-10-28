package io.github.thinkframework.generator.core.configuration;

/**
 * 单例配置管理器
 * @author lixiaobin
 */
public class ConfigurationManager {
    private static volatile ConfigurationManager instance;

    private ConfigurationManager() {

    }

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

}
