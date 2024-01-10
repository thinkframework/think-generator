package io.github.thinkframework.generator.table;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.core.AbstractGenerator;
import io.github.thinkframework.generator.core.chain.ConfigurationResponsibility;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.chain.IDResponsibility;
import io.github.thinkframework.generator.core.command.WalkFileTreeCommand;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.table.chain.TableResponsibility;
import io.github.thinkframework.generator.core.command.FreeMarkerFileCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.TableDatabaseMetaData;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

import javax.sql.DataSource;
import java.io.File;

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
        responsibility(generatorContext -> new TableResponsibility()
                .compose(new IDResponsibility()
                        .compose(new ConfigurationResponsibility()))
                .apply(generatorContext));
        command(new WalkFileTreeCommand(new File(configuration.getTemplate()),new File(configuration.getOutput())));
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    public void generate(DataSource source, String target) throws GeneratorRuntimeException{
        generate(() -> new GeneratorContext()
                        .configuration(configuration)
                        .source(new TableDatabaseMetaData(source).getObject(target)),
                responsibility,
                command);
    }

}
