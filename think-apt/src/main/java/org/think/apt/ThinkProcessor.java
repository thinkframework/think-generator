package org.think.apt;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@SupportedAnnotationTypes("org.think.Meta")
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ThinkProcessor extends AbstractProcessor {
    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
         /*roundEnv.getRootElements()会返回工程中所有的Class
    在实际应用中需要对各个Class先做过滤以提高效率，避免对每个Class的内容都进行扫描*/
        for (Element e : roundEnv.getRootElements()) {
            List<String> statements = new ArrayList<>();
            /*遍历Class内所有元素*/
            for (Element el : e.getEnclosedElements()) {
                /*只处理包含了注解并被修饰为public的Field*/
                if (el.getKind().isField() && el.getAnnotation(Meta.class) != null && el.getModifiers().contains(Modifier.PUBLIC)) {
                    /*获取注解信息，生成代码片段*/
                    Meta meta = el.getAnnotation(Meta.class);
                    int repeat = meta.repeat();
                    String seed = meta.id();
                    String result = "";
                    for (int i = 0; i < repeat; i++) {
                        result += seed;
                    }
                    statements.add("\t\ttarget." + el.getSimpleName() + " = \"" + result + "\";");
                }
            }
            if (statements.size() == 0) {
                return true;
            }

            String enclosingName;
            if (e instanceof PackageElement) {
                enclosingName = ((PackageElement) e).getQualifiedName().toString();
            } else {
                enclosingName = ((TypeElement) e).getQualifiedName().toString();
            }
            /*获取生成类的类名和package*/
            String pkgName = enclosingName.substring(0, enclosingName.lastIndexOf('.'));
            String clsName = e.getSimpleName() + "Gen";
//            log(pkgName + "," + clsName);
            /*创建文件，写入代码内容*/
            try {
                JavaFileObject f = processingEnv.getFiler().createSourceFile(clsName);
//                log(f.toUri().toString());
                Writer writer = f.openWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.println("//Auto generated code, do not modify it!");
                printWriter.println("package " + pkgName + ";");
                printWriter.println("\nimport com.moxun.Actor;\n");
                printWriter.println("public class " + clsName + "<T extends " + e.getSimpleName() + "> implements Actor{");
                printWriter.println("\tprotected T target;");
                printWriter.println("\n\tpublic " + clsName + "(T obj) {");
                printWriter.println("\t\tthis.target = obj;");
                printWriter.println("\t}\n");
                printWriter.println("\t@Override");
                printWriter.println("\tpublic void action() {");
                for (String statement : statements) {
                    printWriter.println(statement);
                }
                printWriter.println("\t}");
                printWriter.println("}");
                printWriter.flush();
                printWriter.close();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }
}
