package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.util.Iterator;

/**
 * @author hdhxby
 * @since 2017/5/16.
 */
public interface GeneratorResponsibility <S,T>{

    default GeneratorContext execute(Iterator<GeneratorResponsibility> iterator,GeneratorContext generatorContext,S source,T target){
        process(iterator,generatorContext,source,target);
        if(iterator.hasNext()){
            GeneratorResponsibility generatorResponsibility = iterator.next();
            generatorResponsibility.execute(iterator,generatorContext,source,target);
            return generatorContext;
        }else{
            return generatorContext;
        }
    }

    void process(Iterator<GeneratorResponsibility> iterator,GeneratorContext generatorContext,S source,T target);

}
