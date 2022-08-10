package com.caldremch.http.core.deprecated.observer

import com.caldremch.http.core.deprecated.observer.IHandleListener

/**
 * Created by Leon on 2022/7/24.
 */
@Deprecated(message = "remove in the future")
interface IHttpEventListenerProvider {
    fun provider(): IHandleListener?
}