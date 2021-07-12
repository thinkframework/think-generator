package io.github.thinkframework.generator.core.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.internal.ClassIntrospector;
import io.github.thinkframework.generator.core.internal.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.internal.builder.BuilderFacade;
import io.github.thinkframework.generator.core.internal.lang.Clazz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 适配器
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClassGeneratorResponsibility<S,T> implements GeneratorResponsibility<S,T> {

    @Override
    public void process(Iterator<GeneratorResponsibility> iterator, GeneratorContext generatorContext,S source,T target) {
        if(!(source instanceof File) || !(target.toString().contains("class"))){
            return;
        }

        Class aClass = null;
        try {
            aClass = new FileLoader().findClass(((File) source).getPath()+File.separator+target);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Clazz clazz = ClassIntrospector.getClazz(aClass);
        String tableName = target.toString();
        //设置表的属性

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter(
            BuilderFacade.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(clazz),
            clazz
        );

        Map result = new HashMap();
        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        generatorContext.getProperties().putAll(result);
    }

    class FileLoader extends ClassLoader {

        @Override
        public Class findClass(String name) throws ClassNotFoundException {
            byte[] b = loadClassFromFile(name);
            return defineClass(name, b, 0, b.length);
        }

        private byte[] loadClassFromFile(String fileName)  {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            byte[] buffer;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = 0;
            try {
                while ( (nextValue = inputStream.read()) != -1 ) {
                    byteStream.write(nextValue);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer = byteStream.toByteArray();
            return buffer;
        }
    }
}
