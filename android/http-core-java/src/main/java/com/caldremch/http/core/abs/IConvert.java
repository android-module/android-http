package com.caldremch.http.core.abs;

import com.caldremch.http.core.framework.base.IBaseResp;
import com.caldremch.http.core.model.ResponseBodyWrapper;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IConvert <R>{
    <T> IBaseResp<T> convert(@NotNull ResponseBodyWrapper<R> responseBodyWrapper, @NotNull Class<T> cls);
}
