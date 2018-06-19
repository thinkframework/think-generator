package org.think.generator.lang;

/**
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
