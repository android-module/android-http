package com.caldremch.http.execute

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.model.ResponseBodyWrapper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


internal class GetExecuteImpl : BaseExecute(), IGetExecute {

    override fun <T> execute(
        request: GetRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>,
        clazz: Class<T>
    ) {

        val httpPath = transferStation.httpPath
        val noCustomerHeader = transferStation.noCustomerHeader
        val showDialog = transferStation.showDialog
        val dialogTips = transferStation.dialogTips
        val dialogHandle = transferStation.dialogHandle
        val requestHandle = transferStation.requestHandle
        val isShowToast = transferStation.isShowToast
        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)

        val handler = go(
            callback,
            clazz,
            dialogHandle, showDialog, dialogTips, requestHandle,
            isShowToast
        )

        val job: Job = GlobalScope.launch {
            if (transferStation.httpParams.isEmpty) {
                val resp = getApi(noCustomerHeader, transferStation.channel).get(pathUrl)
                handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
            } else {
                val resp =getApi(noCustomerHeader, transferStation.channel).get(pathUrl, transferStation.httpParams.urlParams)
                handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
            }
        }

        handler.onSubscribe(job)
    }

    override fun <T> asFutureTask(
        request: GetRequest, transferStation: TransferStation, url: String, clazz: Class<T>
    ): IFutureTask<T> {
        return object : IFutureTask<T> {
            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request, transferStation, url, futureCallback, clazz)
            }
        }
    }

    override fun <T> asFullFutureTask(
        request: GetRequest,
        transferStation: TransferStation,
        url: String,
        clazz: Class<T>
    ): IFullFutureTask<T> {
        return object : IFullFutureTask<T> {
            override fun bindDialogHandle(dialogEventHandle: IDialogHandle): IFullFutureTask<T> {
                transferStation.dialogHandle = dialogEventHandle
                return this
            }

            override fun bindRequestHandle(requestHandleEvent: IRequestHandle): IFullFutureTask<T> {
                transferStation.requestHandle = requestHandleEvent
                return this
            }

            override fun disableToast(): IFullFutureTask<T> {
                transferStation.isShowToast = false
                return this
            }

            override fun showDialog(): IFullFutureTask<T> {
                transferStation.showDialog = true
                return this
            }

            override fun showDialog(message: String): IFullFutureTask<T> {
                transferStation.showDialog = true
                transferStation.dialogTips = message
                return this
            }

            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request, transferStation, url, futureCallback, clazz)
            }
        }
    }

}


