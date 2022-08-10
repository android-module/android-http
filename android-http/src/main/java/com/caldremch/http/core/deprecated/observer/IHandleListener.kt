package com.caldremch.http.core.deprecated.observer

import com.caldremch.http.core.framework.handle.IDialogHandle

/**
 * Created by Leon on 2022/7/10
 */
@Deprecated(message = "remove in the future")
interface IHandleListener: IDialogHandle {
    fun onHttpStart(){}
    fun onHttpEnd(){}

}