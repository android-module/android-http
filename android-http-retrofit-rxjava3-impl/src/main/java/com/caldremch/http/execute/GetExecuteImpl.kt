package com.caldremch.http.execute

import com.caldremch.http.core.AbsCallback
import com.caldremch.http.core.GetRequest
import com.caldremch.http.core.IFutureTask
import com.caldremch.http.core.TransferStation
import com.caldremch.http.core.observer.HttpObservable


class GetExecuteImpl : BaseExecute(), com.caldremch.http.core.IGetExecute {

    override fun <T> execute(
        request: GetRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ) {

        val httpPath = transferStation.httpPath
        val noCustomerHeader = transferStation.noCustomerHeader
        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)
        if (transferStation.httpParams.isEmpty) {
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
                    transferStation.httpParams.urlParams
                ) else api.get(pathUrl, transferStation.httpParams.urlParams),
                callback,
                clazz,
                httpObservable
            )
        }
    }

    override fun <T> asFutureTask(
        request: GetRequest, transferStation: TransferStation, url: String, clazz: Class<T>
    ): IFutureTask<T> {
        return asCancelableFutureTask(request,transferStation, url, clazz, null)
    }

    override fun <T> asCancelableFutureTask(
        request: GetRequest,
        transferStation: TransferStation,
        url: String,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ): IFutureTask<T> {
        return object : IFutureTask<T> {
            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request,transferStation, url, futureCallback, clazz, httpObservable)
            }
        }
    }

}


