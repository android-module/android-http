package com.caldremch.http.execute

import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IBaseResp
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.model.ResponseBodyWrapper


class GetExecuteImpl : BaseExecute(), IGetExecute {

    override suspend fun <T : Any?> execute(
        getRequest: GetRequest,
        ts: TransferStation,
        url: String,
        callback: AbsCallback<IBaseResp<T>>?,
        clazz: Class<T>
    ): IBaseResp<T> {
        val httpPath = ts.httpPath
        val noCustomerHeader = ts.noCustomerHeader
        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)
        val handler = go(
            callback,
            clazz,ts
        )
        var convertResult: IBaseResp<T>
        try {
            if (ts.httpParams.isEmpty) {
                val resp = getApi(noCustomerHeader, ts.channel).get(pathUrl)
                convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
                handler.onSuccess(convertResult)
            } else {
                val resp = getApi(noCustomerHeader, ts.channel).get(
                    pathUrl,
                    ts.httpParams.urlParams
                )
                convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
                handler.onSuccess(convertResult)
            }
        } catch (e: Exception) {
            convertResult = HttpInitializer.getBaseRespFactory().create(null,  e.findCode(),e.message, null,e.findCode()?.toString())
            handleException(e, ts, handler)
        }
        return convertResult
    }



}


