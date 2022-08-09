package com.caldremch.http.core;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Caldremch
 * @date 2019-08-30 09:41
 * @email caldremch@163.com
 * @describe
 **/
@Retention(RetentionPolicy.SOURCE)
@IntDef({Method.GET, Method.POST, Method.FILE})
public @interface Method {
    int GET = 0;
    int POST = 1;
    int FILE = 2;
}
