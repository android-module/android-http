package com.caldremch.http.core.framework.base;

import com.caldremch.http.core.abs.AbsCallback;
import com.caldremch.http.core.framework.handle.IDialogHandle;
import com.caldremch.http.core.framework.handle.IRequestHandle;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IFullFutureTask<T> {
    @NotNull
    IFullFutureTask<T> bindDialogHandle(@NotNull IDialogHandle iDialogHandle);

    @NotNull
    IFullFutureTask<T> bindRequestHandle(@NotNull IRequestHandle iRequestHandle);

    @NotNull
    IFullFutureTask<T> disableToast();

    @NotNull
    IFullFutureTask<T> showDialog();

    @NotNull
    IFullFutureTask<T> showDialog(@NotNull String str);

    void execute(@NotNull AbsCallback<T> absCallback);
}
