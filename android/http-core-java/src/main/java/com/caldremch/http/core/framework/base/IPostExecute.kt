package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.TransferStation

/**
 * Created by Leon on 2022/10/30.
 */
interface IPostExecute : ICommonExecute<PostRequest?> {
    suspend fun <T> execute(
        postRequest: PostRequest,
        ts: TransferStation,
        url: String,
        callback: AbsCallback<IBaseResp<T>>?,
        cls: Class<T>
    ): IBaseResp<T>
}
