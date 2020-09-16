package io.github.thinkframework.generator.strategy;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.util.GeneratorFreeMarker;
import io.github.thinkframework.generator.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void generate(GeneratorContext<S,T> generatorContext) throws GeneratorRuntimeException;

    GeneratorStrategy providers(List<GeneratorProvider> providers);

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    default Boolean instanceOf(GeneratorContext<S,T> generatorContext){
        return true;
    }

    /**
     *
     * @param source
     * @param target
     * @throws GeneratorRuntimeException
     */
    default void generate(S source,T target) throws GeneratorRuntimeException{
        generate(new GeneratorContext<>().source(source).target(target));
    }

    /**
     * 输出
     * @param generatorContext
     */
    default void process(GeneratorContext generatorContext){
        GeneratorConfiguration configuration  = generatorContext.getGeneratorConfiguration();
        //调用输出
        try {
            if (StringUtils.isNotEmpty(configuration.getTemplate())) {//模板目录必须存在
                Files.walkFileTree(Paths.get(new File(configuration.getTemplate()).toURI()), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Objects.requireNonNull(file);
                        Objects.requireNonNull(attrs);
                        if (configuration.getExtensions().stream().noneMatch(extension -> file.toFile().getName().lastIndexOf(extension) > -1)) {
                            return FileVisitResult.CONTINUE;
                        }
                        GeneratorFreeMarker generatorFreeMarker = new GeneratorFreeMarker()
                            .configuration(generatorContext);
                        //输出文件路径
                        File output = new File(generatorFreeMarker.process(generatorContext.getProperties(),
                            file.toFile().getPath(),
                            file.toFile().getPath().replace(configuration.getTemplate().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)),
                                configuration.getOutput().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)))));
                        //生成父文件夹
                        if (Files.notExists(Paths.get(output.getParentFile().toURI()))) {
                            Files.createDirectories(Paths.get(output.getParentFile().toURI()));
                        }
                        //删除已经存在的文件
                        Files.deleteIfExists(Paths.get(output.toURI()));
                        //创建文件
                        Files.createFile(Paths.get(output.toURI()));
                        //输出文件
                        generatorFreeMarker
                            .process(generatorContext.getProperties(), file.toFile(), output);
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
