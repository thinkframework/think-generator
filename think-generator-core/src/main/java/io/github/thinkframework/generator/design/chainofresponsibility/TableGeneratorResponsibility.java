package io.github.thinkframework.generator.design.chainofresponsibility;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.design.adapter.TableClassAdapter;
import io.github.thinkframework.generator.design.builder.TableClassBuilder;
import io.github.thinkframework.generator.design.builder.TableBuilder;
import io.github.thinkframework.generator.internal.sql.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 适配器
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class TableGeneratorResponsibility implements GeneratorResponsibility, Ordered {

    @Override
    public GeneratorContext process(GeneratorContext generatorContext) {
        if(!(generatorContext.getSource() instanceof DataSource)){
            return generatorContext;
        }

        Map result = new HashMap();
        GeneratorDatabaseMetaData generatorDatabaseMetaData = new GeneratorDatabaseMetaData((DataSource) generatorContext.getSource());
        String tableName = (String) generatorContext.getTarget();
        //设置表的属性
        Table table = new TableBuilder()
            .addTable(generatorDatabaseMetaData.getTable(tableName))
            .addColumn(generatorDatabaseMetaData.getColumns(tableName))
            .addPrimaryKey(generatorDatabaseMetaData.getPrimaryKeys(tableName))
            .addIndexInfo(generatorDatabaseMetaData.getIndexInfo(tableName))
//                .addExportedKey(tableFactory.getExportedKeys(tableName))
//                .addImportedKey(tableFactory.getImportedKeys(tableName))
            .build();

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter();
        tableClassAdapter.table(table);
        tableClassAdapter.clazz(new TableClassBuilder().buildClass(table));
        result.put("table", tableClassAdapter);
        result.put("clazz", tableClassAdapter);

        Optional.ofNullable(table.getPrimaryKey())
            .ifPresent(primaryKey -> {//有主键的话
                table.getColumns()
                    .stream().filter(column -> primaryKey.getColumnName().equals(column.getColumnName()))
                    .findFirst().ifPresent(column -> {
                    result.put("id", new ClazzFieldImpl(StringUtils.fieldName(column.getColumnName()), new ClazzImpl(TypesUtils.dataType(column.getDataType()))));
                });
            });
        generatorContext.getProperties().putAll(result);
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
