package com.caldremch.http.core.abs;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IHostConfig {
    boolean enableConfig();
    @NotNull
    String currentUrl();
    @NotNull
    String defaultUrl();
}
