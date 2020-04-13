package io.github.thinkframework.generator;

import io.github.thinkframework.generator.boot.annotation.EnableGenerator;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 命令行运行类
 *
 * @author lixiaobin
 */
@EnableGenerator
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
