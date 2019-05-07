package io.github.thinkframework.generator.interpreter;

public interface Expression {

    boolean interpret(Context ctx);
}
