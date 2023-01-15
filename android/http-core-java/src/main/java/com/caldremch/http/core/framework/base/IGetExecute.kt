package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.TransferStation

/**
 * Created by Leon on 2022/10/30.
 */
interface IGetExecute : ICommonExecute<GetRequest?> {
  suspend  fun <T> execute(
        getRequest: GetRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>?,
        cls: Class<T>
    ): T?
}
