package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.IErrorCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle

/**
 * Created by Leon on 2022/10/30.
 */
interface IRequest<R : IRequest<R>> {
    fun channel(obj: Any?): R
    fun put(str: String, obj: Any?): R
    fun path(str: String, str2: String): R
    fun bindDialogHandle(iDialogHandle: IDialogHandle): R
    fun bindRequestHandle(iRequestHandle: IRequestHandle): R
    fun disableToast(): R
    fun showDialog(): R
    fun showDialog(str: String): R
    fun noCustomerHeader(): R
    fun catchError(errorBlock: IErrorCallback): R
    fun passiveCancelCallbackHandle():R
    suspend fun <T> execute(cls: Class<T>): T?
}
