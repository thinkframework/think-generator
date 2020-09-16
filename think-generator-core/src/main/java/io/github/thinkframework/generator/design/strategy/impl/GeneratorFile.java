package io.github.thinkframework.generator.design.strategy.impl;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.design.templatemethod.AbstractStrategy;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * 根据Class生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public class GeneratorFile extends AbstractStrategy<File,String> {

    /**
     * 生成
     *
     * @return
     */
    @Override
    public void generate(GeneratorContext<File,String> generatorContext) throws GeneratorRuntimeException {
        Assert.notNull(configuration, "配置文件不存在");
        log.info("传入的表名称:{}", generatorContext.getTarget());

        try {
            Files.list(Paths.get(generatorContext.getSource().toURI()))
                .map(Path::getFileName)//获取表名称
                .map(fileName ->
                    GeneratorPrototype.clone(generatorContext)
                        .source(generatorContext.getSource())
                        .target(fileName))//设置环境上下文
                .peek(context -> responsibilitys.stream()
                    .forEach(generatorProvider -> {
                        generatorProvider.process(context);
                    }))//调用所有的提供者,填充数据
                .forEach(this::process);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional.of(new GeneratorContext<>().generatorConfiguration(configuration)
                    .source(generatorContext.getSource())
                    .target(generatorContext.getTarget()))
            .ifPresent(context -> {responsibilitys.stream()
                .forEach(generatorProvider -> {
                    generatorProvider.process(context);
                });
                process(context);
            });
    }
}
