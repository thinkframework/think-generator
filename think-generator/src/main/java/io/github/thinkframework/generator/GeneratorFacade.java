package io.github.thinkframework.generator;

import io.github.thinkframework.generator.command.Command;
import io.github.thinkframework.generator.command.CommandFactory;
import io.github.thinkframework.generator.command.impl.TableGeneratorCommand;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.sql.DataSource;

/**
 * 生成器门面
 * @author lixiaobin
 */
public class GeneratorFacade implements BeanFactoryAware {
    private Logger logger = LoggerFactory.getLogger(GeneratorContext.class);

    private BeanFactory beanFactory;

    private Command command;

    public GeneratorFacade() {

    }

    public GeneratorFacade(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public GeneratorFacade command(Command command) {
        this.command = command;
        return this;
    }


    public GeneratorFacade command() {
        this.command = new TableGeneratorCommand(new Generator(beanFactory));
        return this;
    }

    public GeneratorFacade beanFactory(BeanFactory beanFactory) throws BeansException {
        setBeanFactory(beanFactory);
        return this;
    }

    public void execute() throws GeneratorRuntimeException {
        command.execute();
    }


    public void unDo() throws GeneratorRuntimeException {
        command.unDo();
    }


    /**
     * 默认初始化
     * @throws Exception
     */
    public void execute(DataSource dataSource,String tableName) {
        //TODO 优化成本文
        new GeneratorFacade()
            .command(new CommandFactory(new GeneratorProperties()
//            .setDefaultProperties()
                .setDefaultConfiguration()
                .dataSource(dataSource)
                .tableName(tableName)
                .build()).newCommand())
            .execute();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
