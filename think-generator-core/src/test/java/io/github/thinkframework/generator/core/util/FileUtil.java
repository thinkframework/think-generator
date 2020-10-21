package io.github.thinkframework.generator.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class FileUtil {
    public static void deleteIfExists(File file) throws IOException {
        if(file.exists()) {
            Files.walk(file.toPath())
                .map(Path::toFile).
                sorted(Comparator.reverseOrder())
                .forEach(File::deleteOnExit);
        }
    }
}
