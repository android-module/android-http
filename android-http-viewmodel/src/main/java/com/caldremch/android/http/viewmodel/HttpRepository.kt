package com.caldremch.android.http.viewmodel

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.deprecated.observer.IHandleListener
import com.caldremch.http.core.deprecated.observer.HttpObservable

/**
 * Created by Leon on 2022/7/24.
 *
 * 是一个被订阅的Repository, 被订阅者, 一旦发生变动, 通知所有的订阅者
 */


open class HttpRepository(private val httpEvent: IHandleListener?) : HttpObservable() {

    fun onCancel() {
        debugLog { "${this.javaClass.name}--->取消网络请求..." }
        debugLog { "通知所有的监听者, 当前监听者个数${countObservers()}" }
        setChanged()
        notifyObservers()
    }

    override fun getHttpEventListener(): IHandleListener? {
        return httpEvent
    }
}