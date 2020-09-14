package io.github.thinkframework.generator.test;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

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
        Assert.notNull(generator,"generator创建失败!");
    }
}
