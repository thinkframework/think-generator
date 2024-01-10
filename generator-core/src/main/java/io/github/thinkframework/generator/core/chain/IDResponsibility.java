package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.adapter.ColumnFieldAdapter;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 主键
 * 简单设置为id字段
 * @author hdhxby
 * @since 2017/3/24
 */
public class IDResponsibility extends AbstractResponsibility implements GeneratorResponsibility {


    public IDResponsibility() {
    }

    public IDResponsibility(GeneratorResponsibility before) {
        setBefore(before);
    }

    @Override
    public GeneratorContext apply(GeneratorContext generatorContext) {
        Map result = new HashMap();

        ColumnFieldAdapter id = new ColumnFieldAdapter( new ColumnImpl("id"),
                new ClazzFieldImpl("id", new ClazzImpl(Long.class)));
        result.put("id", id);

        generatorContext.getProperties().putAll(result);
        return generatorContext;
    }
}
