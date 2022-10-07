package com.caldremch.http.core.ext

import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.base.HttpCallback
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.base.IFutureTask

/**
 * Created by Leon on 2022/7/26.
 */

fun post(api: String) = HttpManager.post(api)

fun get(api: String) = HttpManager.get(api)

inline fun <reified RespType> GetRequest.getExec(
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

inline fun <reified RespType> PostRequest.posExec(
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

inline fun <reified RespType> GetRequest.getFutureTask(): IFutureTask<RespType> {
    return this.asFutureTask(RespType::class.java)
}

inline fun <reified RespType> PostRequest.postFutureTask(): IFutureTask<RespType> {
    return this.asFutureTask(RespType::class.java)
}


inline fun <reified RespType> GetRequest.getFullFutureTask(): IFullFutureTask<RespType> {
    return this.asFullFutureTask(RespType::class.java)
}

inline fun <reified RespType> PostRequest.postFullFutureTask(): IFullFutureTask<RespType> {
    return this.asFullFutureTask(RespType::class.java)
}

inline fun <reified RespType> IFutureTask<RespType>.futureTaskExec(
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


inline fun <reified RespType> IFullFutureTask<RespType>.fullFutureTaskExec(
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

