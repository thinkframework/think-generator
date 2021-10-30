package io.github.thinkframework.generator.core.internal.adapter.builder;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.adapter.builder.table.TableClassBuilder;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

public class BuilderFacade {

    private GeneratorConfiguration generatorConfiguration;

    public GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }

    public static BuilderFacade generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        BuilderFacade builderFacade = new BuilderFacade();
        builderFacade.generatorConfiguration = generatorConfiguration;
        return builderFacade;
    }

    public Clazz build(Table table){
        return new TableClassAdapter(table,
            new TableClassBuilder()
            .generatorConfiguration(generatorConfiguration)
            .build(table));
    }
}
