package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.TransferStation

/**
 * Created by Leon on 2022/7/8
 */
interface IPostExecute : ICommonExecute<PostRequest> {
    fun <T> execute(request: PostRequest, transferStation: TransferStation, url:String, callback: AbsCallback<T>, clazz: Class<T>)
}