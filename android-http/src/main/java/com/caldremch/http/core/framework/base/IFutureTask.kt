package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-10 09:41
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/

interface IFutureTask<T>{
    fun execute(futureCallback: AbsCallback<T>)
}

interface  IFullFutureTask<T>{
     fun bindDialogHandle(dialogEventHandle: IDialogHandle): IFullFutureTask<T>
     fun bindRequestHandle(requestHandleEvent: IRequestHandle): IFullFutureTask<T>
    fun disableToast(): IFullFutureTask<T>
    fun showDialog(): IFullFutureTask<T>
    fun showDialog(message:String): IFullFutureTask<T>
    abstract fun execute(futureCallback: AbsCallback<T>)
}