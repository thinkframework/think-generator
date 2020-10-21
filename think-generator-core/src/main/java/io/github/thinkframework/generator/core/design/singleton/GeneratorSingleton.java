package io.github.thinkframework.generator.core.design.singleton;

import io.github.thinkframework.generator.core.design.flyweight.GeneratorFlyweight;

public class GeneratorSingleton {

    private GeneratorFlyweight generatorFlyweight = new GeneratorFlyweight();

    private static volatile GeneratorSingleton instance=null;
    private GeneratorSingleton(){}
    public static synchronized GeneratorSingleton getInstance()
    {
        if(instance==null)
        {
            instance=new GeneratorSingleton();
        }
        return instance;
    }


}
