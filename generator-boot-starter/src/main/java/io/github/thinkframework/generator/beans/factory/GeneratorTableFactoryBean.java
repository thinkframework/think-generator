package io.github.thinkframework.generator.beans.factory;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.table.GeneratorTable;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.FactoryBean;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 工厂模式
 */
public class GeneratorTableFactoryBean implements FactoryBean<GeneratorTable>{

    private GeneratorConfiguration configuration;

    private List<GeneratorResponsibility> responsibilities;

    private GeneratorCommand command;

    public GeneratorTableFactoryBean() {

    }

    public GeneratorTableFactoryBean(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public GeneratorTableFactoryBean configuration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<GeneratorResponsibility> getResponsibilities() {
        return responsibilities;
    }

    public GeneratorTableFactoryBean responsibilities(List<GeneratorResponsibility> responsibilities) {
        this.responsibilities = responsibilities;
        return this;
    }

    public void setResponsibilities(List<GeneratorResponsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public GeneratorCommand getCommand() {
        return command;
    }

    public GeneratorTableFactoryBean command(GeneratorCommand command) {
        this.command = command;
        return this;
    }

    public void setCommand(GeneratorCommand command) {
        this.command = command;
    }

    @Override
    public GeneratorTable getObject() {
        GeneratorTable generatorTable = new GeneratorTable(configuration);
        Iterator<GeneratorResponsibility> iterator = responsibilities.iterator();
        responsibilities.stream().findFirst().ifPresent(first -> {
            generatorTable.setResponsibility(first); // 设置责任链第一个
            for (Function temp = iterator.next();iterator.hasNext();iterator.next()){
                if(iterator.hasNext()){
                    temp = temp.compose(iterator.next()); // 设置责任链
                }
            }
        });
        generatorTable.command(command);
        return generatorTable;
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }

}
