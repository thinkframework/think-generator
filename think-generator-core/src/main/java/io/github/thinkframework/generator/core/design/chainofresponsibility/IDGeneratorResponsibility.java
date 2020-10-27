package io.github.thinkframework.generator.core.design.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.PrimaryKey;
import io.github.thinkframework.generator.core.util.StringUtils;
import io.github.thinkframework.generator.core.util.TypesProxy;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 收集主键
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class IDGeneratorResponsibility implements GeneratorResponsibility, Ordered {

    @Override
    public GeneratorContext process(GeneratorContext generatorContext) {
        if(!(generatorContext.getSource() instanceof DataSource)){
            return generatorContext;
        }

        Map result = new HashMap();

        GeneratorDatabaseMetaData generatorDatabaseMetaData = new GeneratorDatabaseMetaData((DataSource) generatorContext.getSource());
        String tableName = (String) generatorContext.getTarget();
        Map<String,Column> columns = generatorDatabaseMetaData.getColumns(tableName).stream().collect(Collectors.toMap(Column::getColumnName, Function.identity()));
        Collection<PrimaryKey> primaryKeys = generatorDatabaseMetaData.getPrimaryKeys(tableName);
        if(primaryKeys.size() > 1){
            //TODO
//            PrimaryKey primaryKey = primaryKeys.stream().reduce(new PrimaryKeyImpl(),(a,b) -> )
            result.put("id", new ClazzFieldImpl("id", new ClazzImpl(Serializable.class)));
        } else if (primaryKeys.size() == 1) {
            PrimaryKey primaryKey = primaryKeys.stream().findFirst().get();
            result.put("id", new ClazzFieldImpl(StringUtils.fieldName(primaryKey.getColumnName()),
                new ClazzImpl(new TypesProxy(generatorContext.getGeneratorConfiguration()).dataType(columns.get(primaryKey.getColumnName()).getDataType()))));
        } else {
            result.put("id", new ClazzFieldImpl("id", new ClazzImpl(Serializable.class)));
        }
        generatorContext.getProperties().putAll(result);
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
