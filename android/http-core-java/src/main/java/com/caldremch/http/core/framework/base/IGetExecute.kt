package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.TransferStation
import java.lang.reflect.Type

/**
 * Created by Leon on 2022/10/30.
 */
interface IGetExecute : ICommonExecute<GetRequest?> {
  suspend  fun <T> execute(
      getRequest: GetRequest,
      ts: TransferStation,
      url: String,
      callback: AbsCallback<IBaseResp<T>>?,
      cls: Class<T>
    ): IBaseResp<T>


    suspend  fun <T> executeType(
        getRequest: GetRequest,
        ts: TransferStation,
        url: String,
        callback: AbsCallback<IBaseResp<T>>?,
        clazz: Type
    ): IBaseResp<T>
}
