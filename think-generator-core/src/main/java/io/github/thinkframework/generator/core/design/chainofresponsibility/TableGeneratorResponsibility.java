package io.github.thinkframework.generator.core.design.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.design.builder.BuilderFacade;
import io.github.thinkframework.generator.core.internal.GeneratorDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.TableFactory;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机表
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class TableGeneratorResponsibility implements GeneratorResponsibility, Ordered {

    @Override
    public GeneratorContext process(GeneratorContext generatorContext) {
        if(!(generatorContext.getSource() instanceof DataSource)){
            return generatorContext;
        }

        Map result = new HashMap();

        //设置表的属性
        Table table = new TableFactory().getObject(generatorContext);


        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter();
        tableClassAdapter.table(table);
        tableClassAdapter.clazz(BuilderFacade.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(table));
        result.put("table", tableClassAdapter);
        result.put("clazz", tableClassAdapter);
        generatorContext.getProperties().putAll(result);
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
