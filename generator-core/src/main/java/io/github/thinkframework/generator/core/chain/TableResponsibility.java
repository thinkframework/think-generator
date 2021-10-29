package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.TableFactory;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.builder.BuilderFacade;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * 表
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class TableResponsibility implements GeneratorResponsibility<Table> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext<Table> generatorContext) {
        //设置表的属性
        Table table = generatorContext.getSource();

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter(table,
            BuilderFacade.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(table));

        Map result = new HashMap();
        result.put("table", tableClassAdapter);
        result.put("clazz", tableClassAdapter);
        generatorContext.getProperties().putAll(result);

        // 设置环境变量
        Optional.ofNullable(table.getTableName()).ifPresent(name -> {
            generatorContext.getProperties().put("tableName", StringUtils.lowerUnderScore(name));
            generatorContext.getProperties().put("className", StringUtils.upperCamel(name));
            generatorContext.getProperties().put("className_lowerHyphen", StringUtils.lowerHyphen(StringUtils.lowerCamel(name)));
        });
    }

}
