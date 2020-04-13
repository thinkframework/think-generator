package io.github.thinkframework.generator;

import io.github.thinkframework.generator.command.Command;
import io.github.thinkframework.generator.command.impl.TableGeneratorCommand;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.sql.DataSource;

/**
 * 生成器门面
 * 引用单例模式
 *
 * @author lixiaobin
 */
public class GeneratorFacade {
    private Generator tableGenerator;

    private Generator ClassGenerator;

    private void geteratorTable(){

    }

    private void geteratorClass(){

    }
}
