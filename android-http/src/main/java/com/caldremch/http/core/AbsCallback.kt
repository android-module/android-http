package com.caldremch.http.core

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 15:14
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
interface AbsCallback<T> {
    /**
     * 成功回调返回
     * @param data 具体解析类型
     */
    fun onSuccess(data: T?)

    fun onError(e: Throwable?) {}
}