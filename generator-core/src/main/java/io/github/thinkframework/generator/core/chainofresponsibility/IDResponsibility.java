package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.PrimaryKey;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;
import io.github.thinkframework.generator.core.util.StringUtils;
import io.github.thinkframework.generator.core.util.TypesProxy;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 收集主键
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class IDResponsibility<S,T> implements GeneratorResponsibility<S,T> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext generatorContext,S source,T target) {
        if(!(source instanceof DataSource)){
            return;
        }

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
        ColumnImpl column = new ColumnImpl();
        column.setColumnName("id");
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setName("id");
        field.setType(new ClazzImpl(Long.class));
        ColumnFieldAdapter id = new ColumnFieldAdapter(column,field);
        result.put("id", id);

        generatorContext.getProperties().putAll(result);
    }
}
