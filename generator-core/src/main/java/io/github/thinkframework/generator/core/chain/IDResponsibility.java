package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 主键
 * 简单设置为id字段
 * @author hdhxby
 * @since 2017/3/24
 */
public class IDResponsibility<S,T> implements GeneratorResponsibility<T> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext<T> generatorContext) {

        Map result = new HashMap();

//        GeneratorDatabaseMetaData generatorDatabaseMetaData = GeneratorDatabaseMetaData.get((DataSource) source);
//        String tableName = (String) target;
//        Map<String,Column> columns = generatorDatabaseMetaData.getColumns(tableName).stream().collect(Collectors.toMap(Column::getColumnName, Function.identity()));
//        Collection<PrimaryKey> primaryKeys = generatorDatabaseMetaData.getPrimaryKeys(tableName);
//        if (primaryKeys.size() == 1) {
//            PrimaryKey primaryKey = primaryKeys.stream().findFirst().get();
//            result.put("id", new ClazzFieldImpl(StringUtils.lowerCamel(primaryKey.getColumnName()),
//                new ClazzImpl(new TypesProxy(generatorContext.getGeneratorConfiguration()).dataType(columns.get(primaryKey.getColumnName()).getDataType()))));
//        } else {
//            result.put("id", new ClazzFieldImpl("id", new ClazzImpl(Serializable.class)));
//        }
        ColumnFieldAdapter id = new ColumnFieldAdapter( new ColumnImpl("id"),
                new ClazzFieldImpl("id", new ClazzImpl(Long.class)));
        result.put("id", id);

        generatorContext.getProperties().putAll(result);
    }
}
