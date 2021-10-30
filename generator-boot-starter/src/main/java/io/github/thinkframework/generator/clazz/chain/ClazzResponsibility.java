package io.github.thinkframework.generator.clazz.chain;

import io.github.thinkframework.generator.core.chain.AbstractResponsibility;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 适配器
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClazzResponsibility extends AbstractResponsibility implements GeneratorResponsibility {

    @Override
    public GeneratorContext apply(GeneratorContext generatorContext) {
//        Clazz source = generatorContext.getSource();
//        Class aClass = null;
//        try {
//            aClass = new FileLoader().findClass(((File) source).getPath()+File.separator+target);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Clazz clazz = ClassIntrospector.getClazz(aClass);
//        String tableName = target.toString();
//        //设置表的属性
//
//        //适配器,同时提供表和类的字段
//        TableClassAdapter tableClassAdapter = new TableClassAdapter(
//            BuilderFacade.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(clazz),
//            clazz
//        );
//
//        Map result = new HashMap();
//        result.put("table",tableClassAdapter);
//        result.put("clazz",tableClassAdapter);
//
//        generatorContext.getProperties().putAll(result);
        return generatorContext;
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
