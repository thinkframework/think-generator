package io.github.thinkframework.generator.core.command;

import io.github.thinkframework.generator.util.FreeMarkerHelper;
import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.io.File;

/**
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class FreeMarkerFileCommand extends AbstractCommand<File, File, GeneratorContext> implements GeneratorCommand<GeneratorContext> {

    public FreeMarkerFileCommand() {
        super();
    }

    public FreeMarkerFileCommand(File input) {
        super(input);
    }

    public FreeMarkerFileCommand(File input, File output) {
        super(input, output);
    }

    @Override
    public void accept(GeneratorContext generatorContext) {
        new FreeMarkerHelper().file(generatorContext.getProperties(),input,output);
    }
}
