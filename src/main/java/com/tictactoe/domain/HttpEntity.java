package com.tictactoe.domain;

public class HttpEntity<T> {

    private T result;

    public HttpEntity() {
    }

    public HttpEntity(T result) {
        this.result = result;
    }

    /**
     * Getter for result field
     */
    public T getResult() {
        return result;
    }

    /**
     * Setter for result field
     */
    public void setResult(T result) {
        this.result = result;
    }
}