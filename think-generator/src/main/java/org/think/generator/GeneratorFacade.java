package org.think.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.think.generator.context.GeneratorContext;
import org.think.generator.lang.GeneratorRuntimeException;
import org.think.generator.sql.TableFactory;
import org.think.generator.sql.model.impl.TableImpl;
import org.think.generator.util.StringUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;

/**
 *
 */
public class GeneratorFacade {
    private Logger logger = LoggerFactory.getLogger(GeneratorContext.class);
    public GeneratorFacade(){
        GeneratorContext.setContext(new GeneratorContext());
    }
    private void setContext(DataSource dataSource, String tableName) {
        GeneratorContext.setContext(new GeneratorContext(dataSource,tableName));
    }

    public void generatorTable(DataSource dataSource,String tableName) {
        setContext(dataSource,tableName);
        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for(TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
            }catch (Exception e){
                throw new GeneratorRuntimeException(table.getTableName(),e);
            }
        }
    }


    public void generatorTable(DataSource dataSource,String tableName,String modelName) {
        setContext(dataSource,tableName);
        GeneratorContext.getContext().setProperty("moduleName",modelName);
        Collection<TableImpl> tables = new TableFactory(dataSource).getTables(tableName);
        for(TableImpl table : tables) {
            GeneratorContext.getContext().setTableName(table.getTableName());
            try {
                new Generator().generate();
                logger.info(table.getTableName());
            }catch (Exception e){
                e.printStackTrace();
                throw new GeneratorRuntimeException(table.getTableName(),e);
            }
        }
    }
}
