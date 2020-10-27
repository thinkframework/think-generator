package io.github.thinkframework.generator.core.design.builder;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.design.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.design.facade.Facade;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Table;

public class BuilderFacade implements Facade {

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
        return new ClassTableBuilder()
            .generatorConfiguration(generatorConfiguration)
            .buildTable(clazz);
    }

    public Clazz build(Table table){
        return new TableClassBuilder()
            .generatorConfiguration(generatorConfiguration)
            .buildClass(table);
    }


    public ClazzField field(Column column){
        return new ColumnFieldBuilder().generatorConfiguration(generatorConfiguration).buildField(column);
    }


    public ClazzMethod method(Column column){
        return new ColumnMethodBuilder().generatorConfiguration(generatorConfiguration).buildMethod(column);
    }

    public TableClassAdapter adapter(){
        return new TableClassAdapter();
    }
}
