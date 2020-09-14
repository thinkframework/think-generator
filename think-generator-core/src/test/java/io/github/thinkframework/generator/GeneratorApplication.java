package io.github.thinkframework.generator;

import io.github.thinkframework.generator.annotation.EnableGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
