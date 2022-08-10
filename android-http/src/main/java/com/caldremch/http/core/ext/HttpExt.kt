package com.caldremch.http.core.ext

import com.caldremch.http.core.*
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.deprecated.observer.DialogCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.base.HttpCallback
import com.caldremch.http.core.deprecated.observer.HttpObservable
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.PostRequest

/**
 * Created by Leon on 2022/7/26.
 */


fun post(api: String) = HttpManager.post(api)
fun get(api: String) = HttpManager.get(api)


inline fun <reified RespType> GetRequest.exec(
    crossinline error: ((e: Throwable?) -> Unit) = {},
    crossinline success: ((data: RespType?) -> Unit) = {}
) {
    this.execute(RespType::class.java, object : AbsCallback<RespType> {
        override fun onSuccess(data: RespType?) {
            success.invoke(data)
        }
        override fun onError(e: Throwable?) {
            error.invoke(e)
        }

    })
}
inline fun <reified RespType> PostRequest.exec(
    crossinline error: ((e: Throwable?) -> Unit) = {},
    crossinline success: ((data: RespType?) -> Unit) = {}
) {
    this.execute(RespType::class.java, object : AbsCallback<RespType> {
        override fun onSuccess(data: RespType?) {
            success.invoke(data)
        }
        override fun onError(e: Throwable?) {
            error.invoke(e)
        }

    })
}

inline fun <reified RespType> GetRequest.futureTask(): IFutureTask<RespType> {
    return this.asFutureTask(RespType::class.java)
}
inline fun <reified RespType> PostRequest.futureTask(httpObservable: HttpObservable? = null): IFutureTask<RespType> {
    return this.asFutureTask(RespType::class.java)
}

inline fun <reified RespType> IFutureTask<RespType>.futureExec(
    crossinline error: ((e: Throwable?) -> Unit) = {},
    crossinline success: ((data: RespType?) -> Unit) = {}
) {
    this.execute(object : HttpCallback<RespType> {
        override fun onSuccess(data: RespType?) {
            success.invoke(data)
        }

        override fun onError(e: Throwable?) {
            error.invoke(e)
        }
    })
}

@Deprecated(message = "remove in future, useless operation")
inline fun <reified RespType> IFutureTask<RespType>.futureExecLoading(
    crossinline error: ((e: Throwable?) -> Unit) = {},
    crossinline success: ((data: RespType?) -> Unit) = {}
) {
    this.execute(object : DialogCallback<RespType>() {
        override fun onSuccess(data: RespType?) {
            success.invoke(data)
        }

        override fun onError(e: Throwable?) {
            error.invoke(e)
        }
    })
}