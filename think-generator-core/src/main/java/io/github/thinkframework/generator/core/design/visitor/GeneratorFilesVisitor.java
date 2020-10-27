package io.github.thinkframework.generator.core.design.visitor;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class GeneratorFilesVisitor {

    private GeneratorConfiguration generatorConfiguration;

    public GeneratorFilesVisitor generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public void visitor (GeneratorVisitor generatorVisitor) throws IOException {
        Files.walkFileTree(Paths.get(new File(generatorConfiguration.getTemplate()).toURI()), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path input, BasicFileAttributes attrs) throws IOException {
                generatorVisitor.vistor(input.toFile());
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
