package com.caldremch.http.core.abs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Leon on 2022/10/30.
 */
public interface ICommonRequestEventCallback {
   default void onStart() {};

    default void onSuccess(@Nullable Object obj){};

   default void onError(@NotNull Throwable th, boolean showToast){};

   default   void onEnd(){};
}
