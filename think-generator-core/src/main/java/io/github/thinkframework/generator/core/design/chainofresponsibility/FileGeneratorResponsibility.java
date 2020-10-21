package io.github.thinkframework.generator.core.design.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.adapter.TableClassAdapter;
import io.github.thinkframework.generator.core.design.builder.Builder;
import io.github.thinkframework.generator.core.internal.ClassIntrospector;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import org.springframework.core.Ordered;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 适配器
 * @author lixiaobin
 * @since 2017/3/24
 */
public class FileGeneratorResponsibility implements GeneratorResponsibility, Ordered {

    @Override
    public GeneratorContext process(GeneratorContext generatorContext) {
        Map<String, byte[]> results;
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
//        try (StandardJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
//            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
//            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
//            if (task.call()) {
//                results = manager.getClassBytes();
//            }
//        }
//        if(!(generatorContext.getSource() instanceof Class)){
//            return generatorContext;
//        }
        Map result = new HashMap();

        Class aClass = null;

        Clazz clazz = ClassIntrospector.getClazz(aClass);
        //设置表的属性

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter();
        tableClassAdapter.clazz(clazz);
        tableClassAdapter.setTable(Builder.generatorConfiguration(generatorContext.getGeneratorConfiguration()).build(clazz));
        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        generatorContext.getProperties().putAll(result);
        return generatorContext;
    }

    @Override
    public int getOrder() {
        return 0;
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

    class GeneratorStandardJavaFileManager implements StandardJavaFileManager{

        @Override
        public ClassLoader getClassLoader(Location location) {
            return null;
        }

        @Override
        public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
            return null;
        }

        @Override
        public String inferBinaryName(Location location, JavaFileObject file) {
            return null;
        }

        @Override
        public boolean isSameFile(FileObject a, FileObject b) {
            return false;
        }

        @Override
        public boolean handleOption(String current, Iterator<String> remaining) {
            return false;
        }

        @Override
        public boolean hasLocation(Location location) {
            return false;
        }

        @Override
        public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
            return null;
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            return null;
        }

        @Override
        public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
            return null;
        }

        @Override
        public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
            return null;
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> files) {
            return null;
        }

        @Override
        public Iterable<? extends JavaFileObject> getJavaFileObjects(File... files) {
            return null;
        }

        @Override
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> names) {
            return null;
        }

        @Override
        public Iterable<? extends JavaFileObject> getJavaFileObjects(String... names) {
            return null;
        }

        @Override
        public void setLocation(Location location, Iterable<? extends File> path) throws IOException {

        }

        @Override
        public Iterable<? extends File> getLocation(Location location) {
            return null;
        }

        @Override
        public int isSupportedOption(String option) {
            return 0;
        }
    }
}
