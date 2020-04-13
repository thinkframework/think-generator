package io.github.thinkframework.generator.command;

import io.github.thinkframework.generator.context.GeneratorProperties;
import org.springframework.beans.factory.BeanFactory;

/**
 * 工厂方法
 *
 * @author lixiaobin
 */
public class CommandFactory {

    private CommandFactoryImpl commandFactory;

    public CommandFactory(GeneratorProperties generatorPropertis) {
        commandFactory = new CommandFactoryImpl(generatorPropertis);
    }

    public CommandFactory(BeanFactory beanFactory, GeneratorProperties generatorPropertis) {
        commandFactory = new CommandFactoryImpl(generatorPropertis);
    }

    public Command newCommand() {
        return commandFactory.newCommand();
    }


}
