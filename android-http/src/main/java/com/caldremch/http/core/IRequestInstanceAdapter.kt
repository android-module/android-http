package com.caldremch.http.core

/**
 * Created by Leon on 2022/8/8.
 */
interface IRequestInstanceAdapter {

    interface ICancelTag{
        fun cancel()
    }

    fun manager(instance:IRequestInstanceAdapter.ICancelTag)
    fun cancel()
}