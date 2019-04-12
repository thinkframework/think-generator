package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.provider.adapter.TableClassAdapter;
import io.github.thinkframework.generator.sql.TableBuilder;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.Table;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 适配器
 * @author lixiaobin
 * @since 2017/3/24
 */
public class TableGeneratorProvider implements GeneratorProvider{

    @Override
    public Map build(Map map){
        Map result = new HashMap();
        Table table = new TableBuilder().addDataSource(getDataSource())
                .addTableName(map.get("tableName").toString())
                .addColumn(map.get("tableName").toString())
                .addPrimaryKey(map.get("tableName").toString())
//                .addIndexInfo(map.get("tableName").toString())
//                .addExportedKey(map.get("tableName").toString())
//                .addImportedKey(map.get("tableName").toString())
                .build();

        TableClassAdapter tableClassAdapter = new TableClassAdapter(table);

        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        //根据下划线拆分
        String[] prefixs = getProperty("prefix", "").split(",");

        String tableName = table.getTableName();
        for (String prefix: prefixs) {
            if(tableName.toUpperCase().startsWith(prefix)){
                tableName = tableName.toUpperCase().replaceFirst(prefix,"");
                break;
            }
        }

        tableName = StringUtils.replacePrefix(tableName);
        String className = StringUtils.className(tableName);

        result.put("className",className);

        //全小写,JavaScript需要
        result.put("className_lower_case",tableName.toLowerCase());
        result.put("className-lower-case",tableName.replaceAll("_","-").toLowerCase());
        //空格拆分的单词,国际化需要
        result.put("className_space", StringUtils.classNameWithSpace(tableName));
        Optional.ofNullable(table.getPrimaryKey()).ifPresent(primaryKey -> {//有主键的话
            table.getColumns()
                .stream().filter(column -> primaryKey.getColumnName().equals(column.getColumnName()))
                .findFirst().ifPresent(column -> {
                    String id = StringUtils.fieldName(column.getColumnName());
                    result.put("id", new ClazzFieldImpl(id,
                        new ClazzImpl(TypesUtils.dataType(column.getDataType()))));
                });
        });
        return result;
    }

    protected DataSource getDataSource(){
        return GeneratorContext.get().getDataSource();
    }

    protected String getProperty(String key){
        return (String)GeneratorContext.get().getProperty(key);
    }

    public String getProperty(String key,String defaultValue){
        return (String)GeneratorContext.get().getProperty(key,defaultValue);
    }
}
