package io.github.thinkframework.generator;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

/**
 * 命令行运行类
 *
 * @author lixiaobin
 */
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
