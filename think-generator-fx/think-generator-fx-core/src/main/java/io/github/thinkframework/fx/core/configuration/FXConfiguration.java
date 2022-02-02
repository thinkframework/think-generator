package io.github.thinkframework.fx.core.configuration;

import io.github.thinkframework.fx.core.control.MainController;
import io.github.thinkframework.fx.core.control.config.GeneratorConfigControllerFactoryBean;
import io.github.thinkframework.fx.core.control.table.GeneratorTableControllerFactoryBean;
import io.github.thinkframework.fx.core.control.GeneratorAboutController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 所有配置文件
 */
@Configuration
public class FXConfiguration {

    /**
     * 主窗体
     *
     * @return
     */
    @Lazy
    @Bean
    public MainController generatorController() {
        MainController mainController = new MainController();
        return mainController;
    }

    @Lazy
    @Bean
    public GeneratorAboutController generatorAboutController() {
        GeneratorAboutController generatorAboutController = new GeneratorAboutController();
        return generatorAboutController;
    }

    @Lazy
    @Bean
    public GeneratorTableControllerFactoryBean generatorTableControllerFactoryBean(){
        GeneratorTableControllerFactoryBean generatorTableFactoryBean = new GeneratorTableControllerFactoryBean();
        return generatorTableFactoryBean;
    }

    @Lazy
    @Bean
    public GeneratorConfigControllerFactoryBean generatorConfigControllerFactoryBean(){
        GeneratorConfigControllerFactoryBean generatorConfigControllerFactoryBean = new GeneratorConfigControllerFactoryBean();
        return generatorConfigControllerFactoryBean;
    }


}
