package io.github.thinkframework.generator.strategy;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * 根据Class生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public class GeneratorFile implements GeneratorStrategy<File,String> {

    private List<GeneratorProvider> providers;

    private GeneratorConfiguration configuration;

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
                    //TODO 原型模式,有时间再说
                    new GeneratorContext<>().generatorConfiguration(generatorContext.getGeneratorConfiguration())
                        .source(generatorContext.getSource())
                        .target(fileName))//设置环境上下文
                .peek(context -> providers.stream()
                    .forEach(generatorProvider -> {
                        generatorProvider.build(context);
                    }))//调用所有的提供者,填充数据
                .forEach(this::process);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional.of(new GeneratorContext<>().generatorConfiguration(configuration)
                    .source(generatorContext.getSource())
                    .target(generatorContext.getTarget()))
            .ifPresent(context -> {providers.stream()
                .forEach(generatorProvider -> {
                    generatorProvider.build(context);
                });
                process(context);
            });
    }

    @Override
    public GeneratorStrategy providers(List<GeneratorProvider> providers){
        this.providers = providers;
        return this;
    }

    @Override
    public GeneratorStrategy generatorConfiguration(GeneratorConfiguration configuration){
        this.configuration = configuration;
        return this;
    }

}
