package com.caldremch.http.core.framework.base

import com.caldremch.http.core.framework.TransferStation

/**
 * Created by Leon on 2022/7/8
 */
interface ICommonExecute<R>{
    fun <T> asFutureTask(request: R, transferStation: TransferStation, url:String, clazz: Class<T>): IFutureTask<T>
    fun <T> asFullFutureTask(request: R, transferStation: TransferStation,  url:String, clazz: Class<T>): IFullFutureTask<T>
}