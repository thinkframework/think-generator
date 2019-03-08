package io.github.thinkframework.swing.exception;

/**
 * 统一异常信息.
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorFrameRuntimeException extends RuntimeException{
    public GeneratorFrameRuntimeException() {
        super();
    }

    public GeneratorFrameRuntimeException(String message) {
        super(message);
    }


    public GeneratorFrameRuntimeException(Throwable cause) {
        super(cause);
    }

    public GeneratorFrameRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }
}
