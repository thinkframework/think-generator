package io.github.thinkframework.generator.core.command;

import io.github.thinkframework.generator.core.context.GeneratorContext;

public interface GeneratorCommand {

    <T> T  run (GeneratorContext<T> generatorContext);

}
