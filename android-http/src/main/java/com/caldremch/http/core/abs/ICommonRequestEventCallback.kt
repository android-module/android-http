package com.caldremch.http.core.abs


/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 17:56
 *
 * @email caldremch@163.com
 *
 * @describe 可以处理Observer所有声明周期方法, 通用错误处理
 *
 **/
interface ICommonRequestEventCallback {
    fun onStart() {}
    fun onSuccess(data: Any?) {}
    //用户处理错误
    fun onError(e: Throwable)
    fun onEnd() {}
}