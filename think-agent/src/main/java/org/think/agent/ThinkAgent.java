package org.think.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 *
 */
public class ThinkAgent {

    private final static Logger log = LoggerFactory.getLogger(ThinkAgent.class);

    public static void premain(String arg, Instrumentation instrumentation) {
        log.debug(System.getenv("tc"));
        log.debug("{} 方法 premain 参数：{}", instrumentation, arg);
        instrumentation.addTransformer(new ThinkClassFileTransformer());
    }
}
