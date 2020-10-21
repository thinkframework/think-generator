package io.github.thinkframework.generator.core.design.builder;

import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.design.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

public class Builder {

    private GeneratorProperties.GeneratorConfiguration generatorConfiguration;

    public GeneratorProperties.GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }

    public static Builder generatorConfiguration(GeneratorProperties.GeneratorConfiguration generatorConfiguration){
        Builder builder = new Builder();
        builder.generatorConfiguration = generatorConfiguration;
        return builder;
    }

    public Table build(Clazz clazz){
        return new ClassTableBuilder()
            .generatorConfiguration(generatorConfiguration)
            .buildTable(clazz);
    }


    public Clazz build(Table table){
        return new TableClassBuilder()
            .generatorConfiguration(generatorConfiguration)
            .buildClass(table);
    }

    public TableClassAdapter adapter(){
        return new TableClassAdapter();
    }
}
