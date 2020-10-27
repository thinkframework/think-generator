package io.github.thinkframework.generator.core.design.state;

public class ErrorState {
    private Exception exception;
    private String message;
    private Boolean error;

    public Exception getException() {
        return exception;
    }

    public ErrorState exception(Exception exception){
        this.exception = exception;
        return error(true);
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public ErrorState message(String message){
        this.message = message;
        return error(true);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }


    public ErrorState error(Boolean error){
        this.error = error;
        return error(true);
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
