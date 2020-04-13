package io.github.thinkframework.generator.command;

import io.github.thinkframework.generator.command.impl.TableGeneratorCommand;
import io.github.thinkframework.generator.context.GeneratorProperties;
import org.springframework.beans.factory.BeanFactory;

/**
 * 对象工厂
 *
 * @author lixiaobin
 */
public class CommandFactoryImpl {

    private BeanFactory beanFactory;

    private GeneratorProperties generatorPropertis;

    public CommandFactoryImpl(GeneratorProperties generatorPropertis) {
        this.generatorPropertis = generatorPropertis;
    }

    public Command newCommand() {
        return new TableGeneratorCommand();
    }

}
