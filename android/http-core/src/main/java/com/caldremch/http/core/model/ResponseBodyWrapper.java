package com.caldremch.http.core.model;

/**
 * Created by Leon on 2022/10/30.
 */
public final class ResponseBodyWrapper<T> {
    private final T responseBody;

    public ResponseBodyWrapper(T t) {
        this.responseBody = t;
    }

    public final T getResponseBody() {
        return this.responseBody;
    }
}
