package io.github.thinkframework.generator.core.command;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.util.FileUtils;

import java.io.File;

/**
 * 清理输出目录
 * @author lixiaobin
 */
public class ClearCommand implements GeneratorCommand{
    private GeneratorConfiguration generatorConfiguration;

    public ClearCommand(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    @Override
    public void execute(){
        FileUtils.deleteIfExists(new File(generatorConfiguration.getOutput()));
    }
}
