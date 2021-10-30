package io.github.thinkframework.generator.core.command;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.util.FreeMarkerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;

/**
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class WalkFileTreeCommand extends AbstractCommand<File, File, GeneratorContext> implements GeneratorCommand<GeneratorContext> {

    public WalkFileTreeCommand() {
        super();
    }

    public WalkFileTreeCommand(File input) {
        super(input);
    }

    public WalkFileTreeCommand(File input, File output) {
        super(input, output);
    }

    @Override
    public void accept(GeneratorContext context) {
        // 文件生成工具
        FreeMarkerHelper freeMarkerHelper = new FreeMarkerHelper().generatorConfiguration(context.getConfiguration());
        // 过滤出来的文件
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher(String.format("glob:**/*.%s",context.getConfiguration().getExtensions().stream().collect(Collectors.joining(",","{","}"))));
        try {
            // 输入目录
            Path sourceDir = input.toPath();
            // 输出目录
            Path targetDir = sourceDir.getParent().resolve(context.getConfiguration().getOutput());
            // 遍历
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                private Path dir; // 文件夹
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    this.dir = Paths.get(freeMarkerHelper.string(context.getProperties(),
                            targetDir.resolve(sourceDir.relativize(dir)).toString()));
                    if (Files.notExists(this.dir)) { // 文件夹不存在
                        Files.createDirectories(this.dir); // 创建文件夹
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (matcher.matches(file)) {
                        //输出文件路径
                        Path output = Paths.get(freeMarkerHelper.string(context.getProperties(),
                                targetDir.resolve(sourceDir.relativize(file)).toString()));
                        Files.deleteIfExists(output); // 删除已经存在的文件
                        Files.createFile(output); // 创建文件
                        new FreeMarkerFileCommand(file.toFile(),output.toFile())
                                .accept(context); // 生成
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            throw new GeneratorRuntimeException(e);
        }
    }

}
