package io.github.thinkframework.generator.shell;

import io.github.thinkframework.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 命令行
 *
 * @author lixiaobin
 */
@Slf4j
@ShellComponent
public class GeneratorCommand implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @ShellMethod("datasources")
    public void datasources() {
        log.info("{}", Arrays.stream(applicationContext.getBeanNamesForType(DataSource.class)).collect(Collectors.joining("\r\n")));
    }

    @ShellMethod("tables")
    public void tables(String datasource) {
        try (Connection connection = applicationContext.getBean(datasource, DataSource.class).getConnection()) {
            connection.getMetaData().getTables(null, null, null, null);
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    @ShellMethod("generator")
    public void generator(String datasource, String tablename, @ShellOption(defaultValue = "generator") String generator) {
        applicationContext.getBean(Generator.class).tableName(tablename).generate();
    }

//    @ShellMethodAvailability({"tables"})
    public Availability dataSourceExists() {
        return applicationContext.getBeanNamesForType(DataSource.class) != null ? Availability.available() : Availability.unavailable("数据源不存在");
    }

//    @ShellMethodAvailability({"generator"})
    public Availability generatorExists() {
        return applicationContext.getBeanNamesForType(Generator.class) != null ? Availability.available() : Availability.unavailable("生成器不存在");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
