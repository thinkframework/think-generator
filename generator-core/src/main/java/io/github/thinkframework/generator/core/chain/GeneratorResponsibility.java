package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.util.Iterator;

/**
 * @author hdhxby
 * @since 2017/5/16.
 */
public interface GeneratorResponsibility <T>{

    default GeneratorContext execute(Iterator<GeneratorResponsibility> iterator,GeneratorContext<T> generatorContext){
        process(iterator,generatorContext);
        if(iterator.hasNext()){
            GeneratorResponsibility generatorResponsibility = iterator.next();
            generatorResponsibility.execute(iterator,generatorContext);
            return generatorContext;
        }else{
            return generatorContext;
        }
    }

    void process(Iterator<GeneratorResponsibility> iterator,GeneratorContext<T> generatorContext);

}
