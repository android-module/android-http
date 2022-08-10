package com.caldremch.http.core

/**
 * Created by Leon on 2022/7/10
 */
interface IDialogHandle{
    fun dialogShowTiming(dialogTips:String) {}
    fun dialogDismissTiming(){}
}

/**
 * Created by Leon on 2022/7/10
 */
interface IHandleListener: IDialogHandle{
    interface CancelProvider{
        fun onCancel()
    }
    fun onHttpStart(){}
    fun onHttpEnd(){}

}