package io.github.thinkframework.generator.test;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.boot.annotation.EnableGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 命令行运行类
 *
 * @author lixiaobin
 */
//@EnableGenerator
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        GeneratorFactoryBean generator = SpringApplication.run(GeneratorApplication.class, args).getBean(GeneratorFactoryBean.class);
        System.out.println(generator);
    }
}
