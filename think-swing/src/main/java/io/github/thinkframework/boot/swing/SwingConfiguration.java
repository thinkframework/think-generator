package io.github.thinkframework.boot.swing;

import io.github.thinkframework.swing.config.GeneratorConfigureFrame;
import io.github.thinkframework.swing.control.GeneratorControlFrame;
import io.github.thinkframework.swing.control.tree.GeneratorTreePanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

@Configuration
@ImportResource({"classpath:applicationContext1.xml"})
public class SwingConfiguration {

    @Bean
    public GeneratorControlFrame generatorControlFrame(){
        return new GeneratorControlFrame();
    }

    @Bean
    public GeneratorTreePanel generatorTreePanel(){
        return new GeneratorTreePanel();
    }

    @Lazy
    @Bean
    public GeneratorConfigureFrame generatorConfigureFrame(){
        return new GeneratorConfigureFrame();
    }
}
