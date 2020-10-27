package io.github.thinkframework.generator.core.internal;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

import javax.sql.DataSource;

/**
 * 工厂方法
 */
public class TableFactory {

    public Table getObject(GeneratorContext generatorContext){
        GeneratorDatabaseMetaData generatorDatabaseMetaData = new GeneratorDatabaseMetaData((DataSource) generatorContext.getSource());
        String tableName = (String) generatorContext.getTarget();
        return new TableBuilder()
            .addTable(generatorDatabaseMetaData.getTable(tableName))
            .addColumn(generatorDatabaseMetaData.getColumns(tableName))
            .addPrimaryKey(generatorDatabaseMetaData.getPrimaryKeys(tableName))
            .addIndexInfo(generatorDatabaseMetaData.getIndexInfo(tableName))
            .addExportedKey(generatorDatabaseMetaData.getExportedKeys(tableName))
            .addImportedKey(generatorDatabaseMetaData.getImportedKeys(tableName))
            .build();
    }
}
