package com.caldremch.http.core.execption;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public class HostConfigErrorException extends RuntimeException {

    public HostConfigErrorException(String message) {
        super(message);
    }

    public HostConfigErrorException() {
        super("at least config one host");
    }
}
