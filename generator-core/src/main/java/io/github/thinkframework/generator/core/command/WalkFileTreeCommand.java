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
public class WalkFileTreeCommand implements GeneratorCommand<GeneratorContext> {

    private FreeMarkerStringCommand stringCommand = new FreeMarkerStringCommand();

    private FreeMarkerFileCommand command = new FreeMarkerFileCommand();

    @Override
    public void accept(GeneratorContext context) {
        // 文件生成工具
        FreeMarkerHelper freeMarkerHelper = new FreeMarkerHelper().generatorConfiguration(context.getConfiguration());
        // 过滤出来的文件
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher(String.format("glob:**/*.%s",context.getConfiguration().getExtensions().stream().collect(Collectors.joining(",","{","}"))));
        try {
            File file = new File(context.getConfiguration().getTemplate());
            if(!file.exists()){ // 文件不存在,按字符串处理
                stringCommand.apply(context);
                return;
            }
            if(!file.isDirectory()) { // 简单处理
                command.accept(context);
                return;
            }

            // 输入目录
            Path sourceDir = file.toPath();
            // 输出目录
            Path targetDir = sourceDir.getParent().resolve(context.getConfiguration().getOutput());
            // 遍历
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                /**
                 * 预先生成输出目录
                 * @param dir 输入目录
                 * @param attrs 属性
                 * @return
                 * @throws IOException
                 */
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = Paths.get(freeMarkerHelper.string(context.getProperties(),
                            targetDir.resolve(sourceDir.relativize(dir)).toString()));
                    //生成父文件夹
                    if (Files.notExists(targetPath)) {
                        Files.createDirectories(targetPath);
                    }
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * 生成输出文件
                 * @param input
                 * @param attrs
                 * @return
                 */
                @Override
                public FileVisitResult visitFile(Path input, BasicFileAttributes attrs) throws IOException {
                    if (matcher.matches(input)) {
                        //输出文件路径
                        Path output = Paths.get(freeMarkerHelper.string(context.getProperties(),
                                targetDir.resolve(sourceDir.relativize(input)).toString()));
                        //删除已经存在的文件
                        Files.deleteIfExists(output);
                        //创建文件
                        Files.createFile(output);
                        context.getConfiguration().setInput(input.toString());
                        context.getConfiguration().setInput(output.toString());
                        command.accept(context);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            throw new GeneratorRuntimeException(e);
        }
    }

}
