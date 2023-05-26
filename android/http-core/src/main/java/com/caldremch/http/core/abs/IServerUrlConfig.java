package com.caldremch.http.core.abs;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IServerUrlConfig {
    @NotNull
    Map<Object, IHostConfig> channels();
}
