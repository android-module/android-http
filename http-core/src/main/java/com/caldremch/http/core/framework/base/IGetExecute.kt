package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.TransferStation

/**
 * Created by Leon on 2022/7/8
 */
interface IGetExecute : ICommonExecute<GetRequest> {
    fun <T> execute(request: GetRequest, transferStation: TransferStation, url:String, callback: AbsCallback<T>, clazz: Class<T>)
}