package io.github.thinkframework.generator.design.templatemethod;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.design.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.design.strategy.GeneratorStrategy;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 根据Class生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractStrategy<T,S> implements GeneratorStrategy<T,S> {

    protected List<GeneratorResponsibility> responsibilitys;

    protected GeneratorConfiguration configuration;

    @Override
    public GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys){
        this.responsibilitys = responsibilitys;
        return this;
    }

    @Override
    public GeneratorStrategy generatorConfiguration(GeneratorConfiguration configuration){
        this.configuration = configuration;
        return this;
    }


    @Override
    public void generate(GeneratorContext<T,S> generatorContext) throws GeneratorRuntimeException{
        internal(generatorContext);
    }

    public abstract void internal(GeneratorContext<T,S> generatorContext) throws GeneratorRuntimeException;

    protected void responsibilitys(GeneratorContext<T,S> generatorContext) throws GeneratorRuntimeException{
            responsibilitys.stream()
                .forEach(responsibility -> {
                    responsibility.process(generatorContext);
                });//调用所有的提供者,填充数据
            process(generatorContext);
    }

    /**
     * 输出
     * 可以抽到责任链,不值当的
     * @param generatorContext
     */
    protected void process(GeneratorContext<T,S> generatorContext){
        GeneratorConfiguration configuration  = generatorContext.getGeneratorConfiguration();
        //调用输出
        try {
            if (StringUtils.isNotEmpty(configuration.getTemplate())) {//模板目录必须存在
                Files.walkFileTree(Paths.get(new File(configuration.getTemplate()).toURI()), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path input, BasicFileAttributes attrs) throws IOException {
                        Objects.requireNonNull(input);
                        Objects.requireNonNull(attrs);
                        if (configuration.getExtensions().stream().noneMatch(extension -> input.toFile().getName().lastIndexOf(extension) > -1)) {
                            return FileVisitResult.CONTINUE;
                        }
                        //输出文件路径
                        File output = new File(new GeneratorFreeMarker().process(generatorContext.getProperties(),
                            input.toFile().getPath(),
                            input.toFile().getPath().replace(configuration.getTemplate().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)),
                                configuration.getOutput().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)))));
                        log.debug("{} > {}",input,output);
                        //生成父文件夹
                        if (Files.notExists(Paths.get(output.getParentFile().toURI()))) {
                            Files.createDirectories(Paths.get(output.getParentFile().toURI()));
                        }
                        //删除已经存在的文件
                        Files.deleteIfExists(Paths.get(output.toURI()));
                        //创建文件
                        Files.createFile(Paths.get(output.toURI()));
                        //输出文件
                        new GeneratorFreeMarker().process(generatorContext.getProperties(), input.toFile(), output);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } else {
                throw new GeneratorRuntimeException("模板目录不存在");
            }
        } catch (IOException e) {
            throw new GeneratorRuntimeException(e);
        }
    }

}
