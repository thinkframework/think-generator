package io.github.thinkframework.generator.table.chain;

import io.github.thinkframework.generator.core.chain.AbstractResponsibility;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.adapter.builder.BuilderFacade;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;
import io.github.thinkframework.generator.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 表
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class TableResponsibility extends AbstractResponsibility implements GeneratorResponsibility {

    public TableResponsibility() {
    }

    public TableResponsibility(GeneratorResponsibility before) {
        super.setBefore(before);
    }

    @Override
    public GeneratorContext apply(GeneratorContext generatorContext) {
        //设置表的属性
        Table table = (Table) ((GeneratorContext)generatorContext.getSource()).getSource();

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter(table,
            BuilderFacade.generatorConfiguration(generatorContext.getConfiguration()).build(table));

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
        return generatorContext;
    }

}
