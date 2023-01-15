package com.caldremch.http.execute

import com.caldremch.http.CoroutineHandler
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.model.ResponseBodyWrapper
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine


class GetExecuteImpl : BaseExecute(), IGetExecute {

    override suspend fun <T : Any?> execute(
        getRequest: GetRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>?,
        clazz: Class<T>
    ): T? {
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
        var convertResult: T? = null
        try {
            if (transferStation.httpParams.isEmpty) {
                val resp = getApi(noCustomerHeader, transferStation.channel).get(pathUrl)
                convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
                handler.onSuccess(convertResult)
            } else {
                val resp = getApi(noCustomerHeader, transferStation.channel).get(
                    pathUrl,
                    transferStation.httpParams.urlParams
                )
                convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
                handler.onSuccess(convertResult)
            }
        } catch (e: Exception) {
            handleException(e, transferStation, handler)
        }
        return convertResult
    }




}


