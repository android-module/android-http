package com.caldremch.http.core

/**
 * Created by Leon on 2022/7/10
 */
interface IHttpDialogEvent{
    fun dialogShowTiming(){}
    fun dialogDismissTiming(){}
}

/**
 * Created by Leon on 2022/7/10
 */
interface IHttpEventListener: IHttpDialogEvent{
    interface CancelProvider{
        fun onCancel()
    }
    fun onHttpStart(){}
    fun onHttpEnd(){}

}