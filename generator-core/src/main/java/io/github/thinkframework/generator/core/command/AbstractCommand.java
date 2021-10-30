package io.github.thinkframework.generator.core.command;

public abstract class AbstractCommand<S,U,R> implements GeneratorCommand<R>{
    protected S input;
    protected U output;

    public AbstractCommand() {
    }

    public AbstractCommand(S input) {
        this.input = input;
    }

    public AbstractCommand(S input, U output) {
        this.input = input;
        this.output = output;
    }

    public S getInput() {
        return input;
    }

    public void setInput(S input) {
        this.input = input;
    }

    public U getOutput() {
        return output;
    }

    public void setOutput(U output) {
        this.output = output;
    }
}
