package com.caldremch.http.core.ext

import com.caldremch.http.core.*
import com.caldremch.http.core.observer.HttpObservable

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

inline fun <reified RespType> GetRequest.futureTask(httpObservable: HttpObservable? = null): IFutureTask<RespType> {
    return if (httpObservable == null) {
        this.asFutureTask(RespType::class.java)
    } else {
        this.asCancelableFutureTask(RespType::class.java, httpObservable)
    }
}
inline fun <reified RespType> PostRequest.futureTask(httpObservable: HttpObservable? = null): IFutureTask<RespType> {
    return if (httpObservable == null) {
        this.asFutureTask(RespType::class.java)
    } else {
        this.asCancelableFutureTask(RespType::class.java, httpObservable)
    }
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