package io.github.thinkframework.generator.core.design.prototype;

import io.github.thinkframework.generator.core.context.GeneratorContext;

public class GeneratorPrototype implements Cloneable{
    public static GeneratorContext clone(GeneratorContext generatorContext){
        GeneratorContext clone = new GeneratorContext();
        clone.setGeneratorConfiguration(generatorContext.getGeneratorConfiguration());
        return clone;
    }
}
