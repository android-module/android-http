package com.caldremch.http

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.exception.NullDataSuccessException
import com.caldremch.http.adapter.CoroutineRequestAdapter
import kotlinx.coroutines.Job

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 15:38
 *
 * @email caldremch@163.com
 *
 * @describe handle data
 *
 **/
internal class CoroutineHandler<T>(
    private val callback: AbsCallback<T>?,
    private val commonRequestEvent: ICommonRequestEventCallback?,
    private val dialogEvent: IDialogHandle?,
    private val showDialog: Boolean,
    private val dialogTips: String,
    private val requestHandleEvent: IRequestHandle?,
    private val showToast: Boolean
) {

    init {
        commonRequestEvent?.onStart()
        if (showDialog) {
            dialogEvent?.dialogShowTiming(dialogTips)
        }
    }


    fun onSubscribe(d: Job) {
        requestHandleEvent?.onRequestHandle(CoroutineRequestAdapter(d))
    }

    fun onNext(t: T) {
        commonRequestEvent?.onSuccess(t)
        callback?.onSuccess(t)
        if (showDialog) {
            dialogEvent?.dialogDismissTiming()
        }
        commonRequestEvent?.onEnd()
    }

    fun onError(e: Throwable) {

        if (showDialog) {
            dialogEvent?.dialogDismissTiming()
        }

        /**
         * code is 200, but null T ,[AbsCallback.onSuccess] callback
         */
        if (e is NullDataSuccessException) {
            callback?.onSuccess(null)
            commonRequestEvent?.onEnd()
            return
        }

        callback?.onError(e)
        commonRequestEvent?.onError(e, showToast)
        commonRequestEvent?.onEnd()
    }

}