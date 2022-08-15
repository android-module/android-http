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

abstract class IFullFutureTask<T>{
    abstract fun bindDialogHandle(dialogEventHandle: IDialogHandle): IFullFutureTask<T>
    abstract fun bindRequestHandle(requestHandleEvent: IRequestHandle): IFullFutureTask<T>
    abstract fun execute(futureCallback: AbsCallback<T>)
}