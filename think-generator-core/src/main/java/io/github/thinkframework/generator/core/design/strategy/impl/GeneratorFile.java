package io.github.thinkframework.generator.core.design.strategy.impl;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.core.design.templatemethod.AbstractStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 根据文件生成
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorFile extends AbstractStrategy<File,String> {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GeneratorFile.class);
    /**
     * 生成
     *
     * @return
     */
    @Override
    public void internal(GeneratorContext<File,String> generatorContext) throws GeneratorRuntimeException {
        log.info("传入的文件名称:{}", generatorContext.getTarget());

        try {
            Files.list(Paths.get(generatorContext.getSource().toURI()))
                .map(Path::getFileName)//获取表名称
                .filter(fileName -> fileName.equals(generatorContext.getTarget()))
                .map(fileName ->
                    GeneratorPrototype.clone(generatorContext)
                        .source(generatorContext.getSource())
                        .target(fileName))//设置环境上下文
                .peek(super::responsibilitys);
        } catch (IOException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
