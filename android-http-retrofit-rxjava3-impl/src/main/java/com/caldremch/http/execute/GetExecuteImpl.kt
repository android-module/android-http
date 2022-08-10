package com.caldremch.http.execute

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle


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
        val dialogHandle: IDialogHandle? =  transferStation.dialogHandle
        val requestHandle: IRequestHandle? = transferStation.requestHandle

        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)
        if (transferStation.httpParams.isEmpty) {
            go(
                if (noCustomerHeader) noCustomerHeaderApi.get(pathUrl) else api.get(pathUrl),
                callback,
                clazz,
                dialogHandle,showDialog, dialogTips, requestHandle
            )
        } else {
            go(
                if (noCustomerHeader) noCustomerHeaderApi.get(
                    pathUrl,
                    transferStation.httpParams.urlParams
                ) else api.get(pathUrl, transferStation.httpParams.urlParams),
                callback,
                clazz,
                dialogHandle,showDialog, dialogTips, requestHandle
            )
        }
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

}


