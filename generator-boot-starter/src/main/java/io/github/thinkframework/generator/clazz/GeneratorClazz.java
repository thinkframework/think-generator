package io.github.thinkframework.generator.clazz;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.clazz.chain.ClazzResponsibility;
import io.github.thinkframework.generator.core.AbstractGenerator;
import io.github.thinkframework.generator.core.chain.ConfigurationResponsibility;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.chain.IDResponsibility;
import io.github.thinkframework.generator.core.command.FreeMarkerFileCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.ClazzIntrospector;
import io.github.thinkframework.generator.core.internal.lang.Clazz;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public class GeneratorClazz extends AbstractGenerator<Class, String, Clazz> implements Generator<Class, String, Clazz> {

    public GeneratorClazz(GeneratorConfiguration configuration) {
        super(configuration);
        responsibility((GeneratorResponsibility) new ConfigurationResponsibility()
                .compose(new IDResponsibility()
                        .compose(new ClazzResponsibility())));
        command(new FreeMarkerFileCommand());
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    public void generate(Class<?> clazz) throws GeneratorRuntimeException{
        generate(() -> new GeneratorContext<>().configuration(configuration)
                        .source(ClazzIntrospector.getClazz(clazz)),
                responsibility,
                command);
    }

}
