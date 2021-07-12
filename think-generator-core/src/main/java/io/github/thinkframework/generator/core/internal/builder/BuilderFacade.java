package io.github.thinkframework.generator.core.internal.builder;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.builder.clazz.ClassTableBuilder;
import io.github.thinkframework.generator.core.internal.builder.table.TableClassBuilder;
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

    public Table build(Clazz clazz){
        return new TableClassAdapter(
            new ClassTableBuilder()
                .generatorConfiguration(generatorConfiguration)
                .build(clazz),clazz);
    }

    public Clazz build(Table table){
        return new TableClassAdapter(table,
            new TableClassBuilder()
            .generatorConfiguration(generatorConfiguration)
            .build(table));
    }
}
