package com.caldremch.http.core.utils.lazy;

/**
 * Created by Leon on 2022/10/29
 */
public interface Lazy<T> {
    T getValue();
    boolean isInitialized();
}
