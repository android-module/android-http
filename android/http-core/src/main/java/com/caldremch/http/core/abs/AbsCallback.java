package com.caldremch.http.core.abs;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Leon on 2022/10/30.
 */
public interface AbsCallback<T> {
    void onSuccess(@Nullable T t);
    void onError(@Nullable Throwable th);
}
