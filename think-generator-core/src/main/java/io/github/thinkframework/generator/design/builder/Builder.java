package io.github.thinkframework.generator.design.builder;

import io.github.thinkframework.generator.internal.lang.Clazz;
import io.github.thinkframework.generator.internal.sql.databasemetadata.Table;

public class Builder {

    public static Table build(Clazz clazz){
        return new ClassTableBuilder().buildTable(clazz);
    }


    public static Clazz build(Table table){
        return new TableClassBuilder().buildClass(table);
    }
}
