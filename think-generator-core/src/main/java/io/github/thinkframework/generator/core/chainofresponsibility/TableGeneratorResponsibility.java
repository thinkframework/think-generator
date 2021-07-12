package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.TableFactory;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.builder.BuilderFacade;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 手机表
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class TableGeneratorResponsibility implements GeneratorResponsibility<DataSource,String> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext generatorContext,DataSource source,String target) {
        if(!(source instanceof DataSource)){
            return;
        }

        //设置表的属性
        Table table = new TableFactory().getObject(generatorContext,source,target);

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter(table,
            BuilderFacade.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(table));

        Map result = new HashMap();
        result.put("table", tableClassAdapter);
        result.put("clazz", tableClassAdapter);
        generatorContext.getProperties().putAll(result);
    }

}
