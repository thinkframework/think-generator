package io.github.thinkframework.generator.util;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    public static void deleteIfExists(File file) throws RuntimeException {
        try {
            if (file.exists()) {
                Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (IOException e){
            throw new GeneratorRuntimeException(e);
        }
    }


    /**
     * 打开目录
     */
    public static void openDirectory(File file) throws RuntimeException {
        if (file == null){
            throw new NullPointerException("目录不存在");
        }
        if (!file.exists()){
            throw new NullPointerException("目录不存在");
        }
        String osName = System.getProperty("os.name");
        try {
            if ("Mac OS X".equals(osName)) {
                Runtime.getRuntime().exec("open "+file.getAbsolutePath());
            } else {
                Runtime.getRuntime().exec("cmd /c start "+file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 打开文件
     * @param file
     */
    public static void openFile(File file) throws RuntimeException {
        if (file == null){
            throw new NullPointerException("文件不存在");
        }
        if (!file.exists()){
            throw new NullPointerException("文件不存在");
        }
        String osName = System.getProperty("os.name");
        try {
            if ("Mac OS X".equals(osName)) {
                Runtime.getRuntime().exec("open "+file.getAbsolutePath());
            } else {
                Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler "+file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
