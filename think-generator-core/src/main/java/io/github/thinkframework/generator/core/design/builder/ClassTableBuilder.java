package io.github.thinkframework.generator.core.design.builder;

import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.design.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.TableImpl;
import io.github.thinkframework.generator.core.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通过表生成对应的类
 *
 * @author lixiaobin
 */
class ClassTableBuilder {

    private GeneratorProperties.GeneratorConfiguration generatorConfiguration;

    public ClassTableBuilder generatorConfiguration(GeneratorProperties.GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public Table buildTable(Clazz clazz) {
        String tableName = StringUtils.underScoreCase(clazz.getSimpleName());
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
