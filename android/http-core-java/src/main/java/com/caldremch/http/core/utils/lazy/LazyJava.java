package com.caldremch.http.core.utils.lazy;

/**
 * Created by Leon on 2022/10/29
 */
public class LazyJava {
    public static <T> Lazy<T> lazy(JavaDsl<T> initializer){
        return new SynchronizedLazyImpl<>(initializer);
    }
}
