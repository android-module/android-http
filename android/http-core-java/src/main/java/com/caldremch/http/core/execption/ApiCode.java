package com.caldremch.http.core.execption;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Leon on 2022/10/30.
 */
public class ApiCode {
    @NotNull
    public static final ApiCode INSTANCE = new ApiCode();
    public static final int ERROR = 65538;
    public static final int JSON_ERROR = 65539;
    public static final int SUCC_AND_DATA_NULL = 65540;
    public static final int ERROR_STRING_CODE = 65541;
    private ApiCode() {
    }
}
