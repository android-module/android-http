package com.caldremch.http.core

/**
 * Created by Leon on 2022/8/8.
 */
interface IRequestHandle {
    fun onRequestHandle(ctx:IRequestContext)
}