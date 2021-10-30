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
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class FreeMarkerStringCommand implements Function<GeneratorContext,String> {

    @Override
    public String apply(GeneratorContext generatorContext) {
        return new FreeMarkerHelper()
                .generatorConfiguration(generatorContext.getConfiguration())
                .string(generatorContext.getProperties(),
                        generatorContext.getSource().toString());
    }
}
