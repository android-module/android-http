package com.caldremch.http.core.observer;

import java.util.Observable;

/**
 * Created by Leon on 2022/7/24.
 */
@Deprecated
public interface HttpObserver {
    void update(HttpObservable o, Object arg);
}
