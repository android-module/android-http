package com.caldremch.http.core;

import com.caldremch.http.core.framework.GetRequest;
import com.caldremch.http.core.framework.PostRequest;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public class HttpManager {
    private HttpManager() {
    }

    @NotNull
    public static GetRequest get(@NotNull String url) {
        return new GetRequest(url);
    }

    @NotNull
    public static PostRequest post(@NotNull String url) {
        return new PostRequest(url);
    }
}
