package com.caldremch.http.execute

import com.caldremch.http.core.AbsCallback
import com.caldremch.http.core.GetRequest
import com.caldremch.http.core.IFutureTask
import com.caldremch.http.core.observer.HttpObservable


class GetExecuteImpl : BaseExecute(), com.caldremch.http.core.IGetExecute {

    override fun <T> execute(
        request: GetRequest,
        url: String,
        callback: AbsCallback<T>,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ) {

        val httpPath = request.httpPath
        val noCustomerHeader = request.noCustomerHeader
        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)
        if (request.httpParams.isEmpty) {
            go(
                if (noCustomerHeader) noCustomerHeaderApi.get(pathUrl) else api.get(pathUrl),
                callback,
                clazz,
                httpObservable
            )
        } else {
            go(
                if (noCustomerHeader) noCustomerHeaderApi.get(
                    pathUrl,
                    request.httpParams.urlParams
                ) else api.get(pathUrl, request.httpParams.urlParams),
                callback,
                clazz,
                httpObservable
            )
        }
    }

    override fun <T> asFutureTask(
        request: GetRequest, url: String, clazz: Class<T>
    ): IFutureTask<T> {
        return asCancelableFutureTask(request, url, clazz, null)
    }

    override fun <T> asCancelableFutureTask(
        request: GetRequest,
        url: String,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ): IFutureTask<T> {
        return object : IFutureTask<T> {
            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request, url, futureCallback, clazz, httpObservable)
            }
        }
    }

}


