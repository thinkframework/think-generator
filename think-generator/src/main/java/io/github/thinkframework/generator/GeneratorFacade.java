package io.github.thinkframework.generator;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 生成器门面
 */
public class GeneratorFacade {
    private Logger logger = LoggerFactory.getLogger(GeneratorContext.class);

    public GeneratorFacade() {
        GeneratorContext.setContext(new GeneratorContext());
    }

    private void setContext(DataSource dataSource, String tableName) {
        GeneratorContext.setContext(new GeneratorContext(dataSource, tableName));
    }

    private void setContext(String configLocation, DataSource dataSource, String tableName) {
        GeneratorContext.setContext(new GeneratorContext(configLocation, dataSource, tableName));
    }

    /**
     * 生成表
     *
     * @param dataSource
     * @param tableName
     */
    public List<String> generatorTable(DataSource dataSource, String tableName) throws GeneratorRuntimeException {
        setContext(dataSource, tableName);
        Collection<TableImpl> tables = null;
        try {
            tables = new TableFactory(dataSource).getTables(tableName);
            tables.stream().forEach(table -> new Generator().generate());
        } catch (Exception e) {
            throw new GeneratorRuntimeException(e.getMessage(), e);
        } finally {
            return tables.stream().map(talble -> talble.getTableName()).collect(Collectors.toList());
        }
    }

    /**
     * 生成表
     *
     * @param dataSource
     * @param tableName
     * @param modelName
     */
    public void generatorTable(DataSource dataSource, String tableName, String modelName) throws GeneratorRuntimeException {
        setContext(dataSource, tableName);
        GeneratorContext.getContext().setProperty("moduleName", modelName);
        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for (TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
                logger.info(table.getTableName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new GeneratorRuntimeException(table.getTableName(), e);
            }
        }
    }

    /**
     * @param configLocation
     * @param dataSource
     * @param tableName
     * @param modelName
     * @throws GeneratorRuntimeException
     */
    public void generatorTable(String configLocation, DataSource dataSource, String tableName, String modelName) throws GeneratorRuntimeException {
        Properties properties = new Properties();
        properties.setProperty("configLocation", configLocation);
        properties.setProperty("tableName", tableName);
        properties.setProperty("moduleName", modelName);

        GeneratorContext.getContext().setGeneratorProperties(new GeneratorProperties(properties)).setDataSource(dataSource);

        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for (TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
                logger.info(table.getTableName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new GeneratorRuntimeException(table.getTableName(), e);
            }
        }
    }

    /**
     * @param configLocation
     * @param dataSource
     * @param tableName
     * @param modelName
     * @throws GeneratorRuntimeException
     */
    public void generatorTable(String configLocation, String dataSource, String tableName, String modelName) throws GeneratorRuntimeException {
        Properties properties = new Properties();
        properties.setProperty("configLocation", configLocation);
        properties.setProperty("dataSource", dataSource);
        properties.setProperty("tableName", tableName);
        properties.setProperty("moduleName", modelName);

        Collection<TableImpl> tables = new TableFactory(GeneratorContext.getContext().setGeneratorProperties(new GeneratorProperties(properties)).getDataSource()).getTables(tableName);
        for (TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
                logger.info(table.getTableName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new GeneratorRuntimeException(table.getTableName(), e);
            }
        }
    }
}
