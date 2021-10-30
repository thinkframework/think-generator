package io.github.thinkframework.generator.table;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.TableDatabaseMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * 批量生成的方法
 * 具体的Generator不提供批量生成的方法
 * 不提高复杂性
 * @author hdhxby
 * @version 1.0.0
*  @since 1.0.0
 */
public class GeneratorTables  {

    private static final Logger log = LoggerFactory.getLogger(GeneratorTables.class);

    private GeneratorTable generator;

    public GeneratorTables(GeneratorTable generator) {
        this.generator = generator;
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    public void generate(DataSource source, String target) throws GeneratorRuntimeException{
        new TableDatabaseMetaData(source).getTables(target)
                .stream()
                .peek(table -> log.info("{} 生成中.",table))
                .forEach(table -> generator.generate(source,target));
    }

}
