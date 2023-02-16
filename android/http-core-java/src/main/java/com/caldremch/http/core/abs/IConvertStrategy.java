package com.caldremch.http.core.abs;

import com.caldremch.http.core.framework.base.IBaseResp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IConvertStrategy {
    <T> boolean isStreamConvert(@NotNull Class<T> cls);
    <T> T convertStream(@Nullable InputStream inputStream);
    <T> IBaseResp<T> convertCommon(@NotNull String str, @NotNull Class<T> cls);
}
