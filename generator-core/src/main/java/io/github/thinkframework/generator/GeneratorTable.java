package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.AbstractGenerator;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.TableFactory;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public class GeneratorTable extends AbstractGenerator<DataSource, String, Table> implements Generator<DataSource, String, Table> {

    public GeneratorTable(GeneratorConfiguration configuration) {
        super(configuration);
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    @Override
    public void generate(DataSource source, String target) throws GeneratorRuntimeException{
        generate(() -> new TableFactory().getObject(source, target));
    }

}
