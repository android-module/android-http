package com.caldremch.http.core.execption;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Leon on 2022/10/30.
 */
public class ApiHttpException extends RuntimeException {
    private final int code;

    public ApiHttpException(int code, @Nullable String message) {
        super(message);
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }
}