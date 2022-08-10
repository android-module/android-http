package com.caldremch.http.core.deprecated.observer;

/**
 * Created by Leon on 2022/7/24.
 */
@Deprecated
public interface HttpObserver {
    void update(HttpObservable o, Object arg);
}
