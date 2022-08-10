package com.caldremch.http.core.android.vm

import com.caldremch.http.core.IHandleListener

/**
 * Created by Leon on 2022/7/24.
 */
interface IHttpEventListenerProvider {
    fun provider():IHandleListener?
}