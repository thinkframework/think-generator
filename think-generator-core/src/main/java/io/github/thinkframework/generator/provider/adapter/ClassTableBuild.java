package io.github.thinkframework.generator.provider.adapter;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.impl.ClazzPackageImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.sql.model.Table;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import io.github.thinkframework.generator.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通过表生成对应的类
 *
 * @author lixiaobin
 */
public class ClassTableBuild {

    private GeneratorContext generatorContext;

    public ClassTableBuild(GeneratorContext generatorContext) {
        this.generatorContext = generatorContext;
    }

    public Clazz buildTable(Clazz clazz) {
        generatorContext.getGeneratorConfiguration().getPrefixs().forEach(prefix -> {
            //TODO 要不要遍历
            if (table.getTableName().toLowerCase().startsWith(prefix.toLowerCase())) {//生成对象的时候忽略表名前置
                ((TableImpl) table).setTableName(table.getTableName().toLowerCase().replace(prefix.toLowerCase(), ""));
            }
        });
        String tableName = StringUtils.className(table.getTableName());
        TableImpl table = new TableImpl(tableName);
        table.setColumns(buildField(table));

//        clazz.setImportedFields(getImportedKeyFields(table));
//        clazz.setExportedFields(getExportedKeyFields(table));
//
//        clazz.setImportedMethods(getImportedKeyMethods(table));
//        clazz.setExportedMethods(getExportedKeyMethods(table));

        Clazz proxy = clazz;
        return proxy;
    }

    /**
     * 根据列生成生成字段信息
     *
     * @param table
     * @return 字段信息
     */
    private Set<ClazzField> buildColumn(Clazz clazz) {
        return clazz.getFields().stream()
            .map(column -> {
                ColumnFieldAdapter columnFieldAdapter = new ColumnFieldAdapter(column);
                return columnFieldAdapter;
            }).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
