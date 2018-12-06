package org.think.generator.provider;

import org.think.generator.context.GeneratorContext;
import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.reflect.impl.ClazzFieldImpl;
import org.think.generator.provider.adapter.TableClassAdapter;
import org.think.generator.sql.TableBuilder;
import org.think.generator.sql.model.Table;
import org.think.generator.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 适配器
 * @author lixiaobin
 * @since 2017/3/24
 */
public class TableGeneratorProvider implements GeneratorProvider{
    protected Map result = new HashMap();

    @Override
    public Map build(Map map){
        Table table = new TableBuilder().addDataSource(getDataSource())
                .addTableName(getTableName())
                .addColumn(getTableName())
                .addPrimaryKey(getTableName())
                .addIndexInfo(getTableName())
//                .addExportedKey(getTableName())
//                .addImportedKey(getTableName())
                .build();

        TableClassAdapter tableClassAdapter = new TableClassAdapter(table);

        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        //根据下划线拆分
        String[] prefixs = getProperty("prefix", "").split(",");

        String tableName = getTableName();
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
        //TODO 主键策略 写死的String 优化成数据库获取
        if(table.getPrimaryKey() != null) {
            String columnName = table.getPrimaryKey().getColumnName();
            for (String prefix : prefixs) {
                if (columnName.toUpperCase().startsWith(prefix)) {
                    columnName = columnName.toUpperCase().replaceFirst(prefix, "");
                    break;
                }
            }
            String id = StringUtils.fieldName(columnName);
            result.put("id", new ClazzFieldImpl(id, new ClazzImpl(String.class)));
        }else{
            result.put("id", new ClazzFieldImpl("id", new ClazzImpl(String.class)));
        }
        return result;
    }

    protected DataSource getDataSource(){
        return GeneratorContext.getContext().getDataSource();
    }

    protected String getTableName(){
        return getProperty("tableName");
    }

    protected String getProperty(String key){
        return (String)GeneratorContext.getContext().getProperty(key);
    }

    public String getProperty(String key,String defaultValue){
        return (String)GeneratorContext.getContext().getProperty(key,defaultValue);
    }
}
