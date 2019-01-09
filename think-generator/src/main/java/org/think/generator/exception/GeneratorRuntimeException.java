package org.think.generator.exception;

/**
 * 生成器异常
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorRuntimeException extends RuntimeException{
    public GeneratorRuntimeException() {
        super();
    }

    public GeneratorRuntimeException(String message) {
        super(message);
    }


    public GeneratorRuntimeException(Throwable cause) {
        super(cause);
    }

    public GeneratorRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }
}
