package io.github.thinkframework.generator;

import io.github.thinkframework.generator.boot.EnableGenerator;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.table.GeneratorTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 命令行运行类
 *
 * @author hdhxby
 */
@EnableGenerator
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeneratorApplication.class, args);
    }

    @Bean
    public GeneratorTable generatorTable(GeneratorConfiguration generatorConfiguration) {
        return new GeneratorTable(generatorConfiguration);
    }
}
