package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.strategy.GeneratorStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public class GeneratorImpl<S,T> implements Generator<S,T> {

    protected GeneratorConfiguration configuration;

    private List<GeneratorStrategy> strategies = new ArrayList<>();

    public GeneratorImpl(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public Generator strategies(List<GeneratorStrategy> strategies) {
        this.strategies = strategies;
        return this;
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    @Override
    public void generate(S source, T target) throws GeneratorRuntimeException{
        // 根据数据库生成
        for(GeneratorStrategy strategy : strategies) {
            strategy.execute(configuration,source,target);
        }
    }
}
