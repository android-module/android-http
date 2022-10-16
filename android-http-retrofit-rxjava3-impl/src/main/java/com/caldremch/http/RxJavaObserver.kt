package com.caldremch.http

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestContext
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.execption.NullDataSuccessException
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

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
internal class RxJavaObserver<T>(
    private val callback: AbsCallback<T>?,
    private val commonRequestEvent: ICommonRequestEventCallback?,
    private val dialogEvent: IDialogHandle?,
    private val showDialog:Boolean,
    private val dialogTips:String,
    private val requestHandleEvent: IRequestHandle?,
    private val showToast:Boolean
) : Observer<T> {

    init {
        commonRequestEvent?.onStart()
        if(showDialog){
            dialogEvent?.dialogShowTiming(dialogTips)
        }
    }
    private class DisposableAdapter(private val d: Disposable) : IRequestContext {
        override fun cancel() {
            if(d.isDisposed.not()){
                debugLog{"释放请求"}
                d.dispose()
            }
        }
    }

    override fun onSubscribe(d: Disposable) {
        requestHandleEvent?.onRequestHandle(DisposableAdapter(d))
    }

    override fun onNext(t: T) {
        commonRequestEvent?.onSuccess(t)
        callback?.onSuccess(t)
        if (showDialog) {
            dialogEvent?.dialogDismissTiming()
        }
        commonRequestEvent?.onEnd()
    }

    override fun onError(e: Throwable) {

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


    override fun onComplete() {
    }

}