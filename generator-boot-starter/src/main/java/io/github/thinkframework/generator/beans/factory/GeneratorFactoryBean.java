package io.github.thinkframework.generator.beans.factory;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.GeneratorTable;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.FactoryBean;

import java.util.Arrays;
import java.util.List;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements FactoryBean<Generator>{

    private GeneratorConfiguration configuration;

    private List<GeneratorResponsibility> responsibilities;

    private GeneratorCommand command;

    public GeneratorFactoryBean() {

    }

    public GeneratorFactoryBean(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<GeneratorResponsibility> getResponsibilities() {
        return responsibilities;
    }

    public GeneratorFactoryBean responsibilities(List<GeneratorResponsibility> responsibilities) {
        this.responsibilities = responsibilities;
        return this;
    }

    public void setResponsibilities(List<GeneratorResponsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public GeneratorCommand getCommand() {
        return command;
    }

    public GeneratorFactoryBean command(GeneratorCommand command) {
        this.command = command;
        return this;
    }

    public void setCommand(GeneratorCommand command) {
        this.command = command;
    }

    @Override
    public Generator getObject() {
        return new GeneratorTable(configuration)
                .responsibilitys(responsibilities);
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }

}
