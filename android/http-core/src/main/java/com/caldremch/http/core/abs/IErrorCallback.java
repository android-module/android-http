package com.caldremch.http.core.abs;

import org.jetbrains.annotations.Nullable;

/**
 * @author Leon
 * @date 2023/1/15
 * @desc 错误回调
 */
public interface IErrorCallback {
    void onError(@Nullable Throwable throwable);
}
