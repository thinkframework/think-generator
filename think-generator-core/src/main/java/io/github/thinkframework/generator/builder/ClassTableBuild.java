package io.github.thinkframework.generator.builder;

import io.github.thinkframework.generator.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.sql.model.Column;
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

    public Table buildTable(Clazz clazz) {
        String tableName = StringUtils.tableName(clazz.getSimpleName());
        TableImpl table = new TableImpl();
        table.setTableName(tableName);
        table.setColumns(buildColumn(clazz));
        return table;
    }

    /**
     * 根据列生成生成字段信息
     *
     * @param clazz
     * @return 字段信息
     */
    private Set<Column> buildColumn(Clazz clazz) {
        return clazz.getFields().stream()
            .map(column -> {
                ColumnFieldAdapter columnFieldAdapter = new ColumnFieldAdapter(column);
                return columnFieldAdapter;
            }).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
