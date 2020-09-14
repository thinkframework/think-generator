package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.ClazzBuilder;
import io.github.thinkframework.generator.lang.ClazzFactory;
import io.github.thinkframework.generator.provider.adapter.TableClassAdapter;
import org.springframework.core.Ordered;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 适配器
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClassGeneratorProvider implements GeneratorProvider, Ordered {

    @Override
    public GeneratorContext build(GeneratorContext generatorContext) {
        if(!(generatorContext.getSource() instanceof Class)){
            return generatorContext;
        }
        Map result = new HashMap();
        ClazzFactory clazzFactory = new ClazzFactory((Class) generatorContext.getSource());
        String tableName = generatorContext.getTarget();
        //设置表的属性
        Clazz clazz = new ClazzBuilder()
                .addClass(clazzFactory.getClazz())
                .addColumn(tableFactory.getColumns(tableName))
                .build();

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter();
        tableClassAdapter.clazz(clazz);
        tableClassAdapter.clazz(new TableClassBuild(generatorProperties).buildClass(table));
        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        //根据下划线拆分
        String[] prefixs = generatorProperties.getProperty("prefix", "").split(",");
        for (String prefix: prefixs) {
            if(tableName.toUpperCase().startsWith(prefix)){
                tableName = tableName.toUpperCase().replaceFirst(prefix,"");
                break;
            }
        }

        result.put("className",tableClassAdapter.getClazz().getSimpleName());

        //全小写,JavaScript需要
        result.put("className_lower_case",tableName.toLowerCase());
        result.put("className-lower-case",tableName.replaceAll("_","-").toLowerCase());
        //空格拆分的单词,国际化需要
        result.put("className_space", StringUtils.classNameWithSpace(tableName));

        Optional.ofNullable(table.getPrimaryKey())
            .ifPresent(primaryKey -> {//有主键的话
                table.getColumns()
                .stream().filter(column -> primaryKey.getColumnName().equals(column.getColumnName()))
                .findFirst().ifPresent(column -> {
                    result.put("id", new ClazzFieldImpl(StringUtils.fieldName(column.getColumnName()), new ClazzImpl(TypesUtils.dataType(column.getDataType()))));
                });
        });
        generatorProperties.getProperties().putAll(result);
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
