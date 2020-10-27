package io.github.thinkframework.generator.core.design.visitor;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.util.GeneratorFreeMarker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;

public class GeneratorFileVistor implements GeneratorVisitor{

    private GeneratorConfiguration generatorConfiguration;

    private GeneratorContext generatorContext;

    public GeneratorFileVistor generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public GeneratorVisitor generatorContext(GeneratorContext generatorContext){
        this.generatorContext = generatorContext;
        return this;
    }

    @Override
    public void vistor(File input) throws IOException {
        if (generatorConfiguration.getExtensions().stream()
            .anyMatch(extension -> input.getName().lastIndexOf(extension) > -1)) {
            //输出文件路径
            File output = new File(new GeneratorFreeMarker().generatorConfiguration(generatorConfiguration).string(generatorContext.getProperties(),
                input.getPath().replace(generatorConfiguration.getTemplate().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)),
                    generatorConfiguration.getOutput().replaceAll("[\\/]", Matcher.quoteReplacement(File.separator)))));
            //生成父文件夹
            if (Files.notExists(Paths.get(output.getParentFile().toURI()))) {
                Files.createDirectories(Paths.get(output.getParentFile().toURI()));
            }
            //删除已经存在的文件
            Files.deleteIfExists(Paths.get(output.toURI()));
            //创建文件
            Files.createFile(Paths.get(output.toURI()));
            //输出文件
            new GeneratorFreeMarker().generatorConfiguration(generatorConfiguration).file(generatorContext.getProperties(), input);
        }
    }
}
