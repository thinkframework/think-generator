package io.github.thinkframework.generator.core.strategy;

import io.github.thinkframework.generator.core.chainofresponsibility.*;
import io.github.thinkframework.generator.core.strategy.impl.GeneratorTable;

public class GeneratorStragetyFactory {

    private static GeneratorStragetyFactory instance =  new GeneratorStragetyFactory();

    private GeneratorStragetyFactory(){

    }

    public static GeneratorStragetyFactory getInstance(){
        return instance;
    }

    public GeneratorStrategy generatorTable(){
        return new GeneratorTable()
            .addResponsibility(configurationGeneratorResponsibility())
            .addResponsibility(tableGeneratorResponsibility())
            .addResponsibility(idGeneratorResponsibility())
            .addResponsibility(freeMarkerGeneratorResponsibility());
    }

    public GeneratorResponsibility configurationGeneratorResponsibility(){
        return new ConfigurationResponsibility();
    }

    public GeneratorResponsibility tableGeneratorResponsibility(){
        return new TableResponsibility();
    }

    public GeneratorResponsibility idGeneratorResponsibility(){
        return new IDResponsibility();
    }


    public GeneratorResponsibility freeMarkerGeneratorResponsibility(){
        return new FreeMarkerResponsibility();
    }

}
