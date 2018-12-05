package org.think.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 *
 */
public class ThinkAgent {
    public static void premain(String arg, Instrumentation instrumentation) {
        System.err.println("装载成功 方法 premain 参数：" + arg);
//        instrumentation.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                return new byte[0];
//            }
//        });
    }
}
