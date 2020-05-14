package io.github.thinkframework.generator.shell;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.GeneratorFactoryBean;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 命令行 Facde门面模式
 *
 * @author lixiaobin
 */
@Slf4j
@ShellComponent
public class GeneratorShell implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ShellMethod("datasources")
    public void datasources() {
        log.info("{}", Arrays.stream(applicationContext.getBeanNamesForType(DataSource.class)).collect(Collectors.joining("\r\n")));
    }

    @ShellMethod("tables")
//    @ShellMethodAvailability({"tablesAvailability"})
    public void tables(String datasource) {
        try (Connection connection = applicationContext.getBean(datasource, DataSource.class).getConnection()) {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
            while (resultSet.next()){
                log.info(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    @ShellMethod("generator")
//    @ShellMethodAvailability({"generatorExists"})
    public void generator(String datasource, String tablename, @ShellOption(defaultValue = "generator") String generator) throws Exception {
        applicationContext.getBean(GeneratorFactoryBean.class)
            .getObject()
            .tableName(tablename)
            .generate();
    }

//    @ShellMethodAvailability({"tables"})
    public Availability tablesAvailability() {
        return applicationContext.getBeanNamesForType(DataSource.class) != null ? Availability.available() : Availability.unavailable("数据源不存在");
    }

    public Availability generatoAvailability() {
        return applicationContext.getBeanNamesForType(Generator.class) != null ? Availability.available() : Availability.unavailable("生成器不存在");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
