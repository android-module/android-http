package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle

/**
 * Created by Leon on 2022/7/8
 */
interface IRequest<out R : IRequest<R>> {
    fun put(key: String, value: Any?): R
    fun path(pathName: String, value: String): R
    fun bindDialogHandle(dialogEventHandle: IDialogHandle): R
    fun bindRequestHandle(requestHandleEvent: IRequestHandle): R
    fun disableToast(): R
    fun showDialog(message:String): R
    fun noCustomerHeader(): R
    fun <T> execute( clazz: Class<T>,callback: AbsCallback<T>)
    fun <T> asFutureTask( clazz: Class<T>): IFutureTask<T>
    fun <T> asFullFutureTask( clazz: Class<T>): IFullFutureTask<T>

}


