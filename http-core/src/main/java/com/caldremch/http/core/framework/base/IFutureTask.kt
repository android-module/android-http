package com.caldremch.http.core.framework.base

import com.caldremch.http.core.abs.AbsCallback

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

interface IFutureTask<T> {
    fun execute(futureCallback: AbsCallback<T>)
}

