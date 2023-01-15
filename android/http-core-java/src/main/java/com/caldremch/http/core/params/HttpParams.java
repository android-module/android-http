package com.caldremch.http.core.params;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Leon on 2022/10/30.
 */
public class HttpParams {

    @NotNull
    private Map<String, Object> urlParamsMap = new LinkedHashMap();

    @NotNull
    public final Map<String, Object> getUrlParamsMap() {
        return this.urlParamsMap;
    }

    @NotNull
    public final Map<String, Object> getUrlParams() {
        return this.urlParamsMap;
    }

    public final void setUrlParamsMap(@NotNull Map<String, Object> map) {
        this.urlParamsMap = map;
    }

    public HttpParams() {
    }

    public HttpParams(@NotNull String key, @NotNull String value) {
        put(key, value);
    }

    public HttpParams(@NotNull String key, @NotNull File file) {
        put(key, file);
    }

    public final void put(@NotNull String key, @NotNull Object value) {
        this.urlParamsMap.put(key, value);
    }

    public final boolean isEmpty() {
        return this.urlParamsMap.isEmpty();
    }
}
