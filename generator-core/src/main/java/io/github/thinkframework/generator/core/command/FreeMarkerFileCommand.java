package io.github.thinkframework.generator.core.command;

import io.github.thinkframework.generator.util.FreeMarkerHelper;
import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.io.File;

/**
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class FreeMarkerFileCommand implements GeneratorCommand<GeneratorContext> {

    @Override
    public void accept(GeneratorContext generatorContext) {
        new FreeMarkerHelper()
                .file(generatorContext.getProperties(),
                    new File(generatorContext.getConfiguration().getInput()),
                    new File(generatorContext.getConfiguration().getOutput()));
    }
}
