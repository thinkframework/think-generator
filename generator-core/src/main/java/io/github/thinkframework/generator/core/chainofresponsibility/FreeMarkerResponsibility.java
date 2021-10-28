package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.util.FreeMarkerHelper;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * 手机表
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class FreeMarkerResponsibility<S,T> implements GeneratorResponsibility<S,T> {

    private static final Logger log = LoggerFactory.getLogger(FreeMarkerResponsibility.class);

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext generatorContext,S source,T target) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher(String.format("glob:**/*.%s",generatorContext.getGeneratorConfiguration().getExtensions().stream().collect(Collectors.joining(",","{","}"))));
        try {
            Path sourceDir = Paths.get(new File(generatorContext.getGeneratorConfiguration().getTemplate()).toURI());
            Path targetDir = sourceDir.getParent().resolve(generatorContext.getGeneratorConfiguration().getOutput());

//            Files.walk(sourceDir,FileVisitOption.FOLLOW_LINKS);

            FreeMarkerHelper freeMarkerHelper = new FreeMarkerHelper().generatorConfiguration(generatorContext.getGeneratorConfiguration());
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = Paths.get(freeMarkerHelper.string(generatorContext.getProperties(), targetDir.resolve(sourceDir.relativize(dir)).toString()));
                    //生成父文件夹
                    if (Files.notExists(targetPath)) {
                        log.info("createDictionary: {}.",targetPath.toString());
                        Files.createDirectories(targetPath);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    try {
                        if (matcher.matches(file)) {
                            //输出文件路径
                            Path output = Paths.get(freeMarkerHelper.string(generatorContext.getProperties(), targetDir.resolve(sourceDir.relativize(file)).toString()));
                            //删除已经存在的文件
                            Files.deleteIfExists(output);
                            //创建文件
                            Files.createFile(output);

//                             new SpelExpressionParser().parseExpression("${appName}", new TemplateParserContext("${","}"))
//                                .getValue(new StandardEvaluationContext(generatorContext.getGeneratorConfiguration()));

                            log.info("file: {} -> {}.",sourceDir.relativize(file),targetDir.relativize(output));
                            //输出文件
                            freeMarkerHelper.file(generatorContext.getProperties(), file.toFile(),output.toFile());
                        }
                    } catch (IOException e) {
                        throw new GeneratorRuntimeException(e);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            throw new GeneratorRuntimeException(e);
        }
    }
}
