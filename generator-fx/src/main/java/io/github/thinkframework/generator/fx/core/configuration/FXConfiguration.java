package io.github.thinkframework.generator.fx.core.configuration;

import io.github.thinkframework.generator.fx.controler.GeneratorMainController;
import io.github.thinkframework.generator.fx.controler.table.GeneratorTableControllerFactoryBean;
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
    public GeneratorMainController generatorController() {
        GeneratorMainController generatorMainController = new GeneratorMainController();
        return generatorMainController;
    }

    @Lazy
    @Bean
    public GeneratorTableControllerFactoryBean generatorTableControllerFactoryBean(){
        GeneratorTableControllerFactoryBean generatorTableFactoryBean = new GeneratorTableControllerFactoryBean();
        return generatorTableFactoryBean;
    }

}
