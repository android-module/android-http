package com.caldremch.http

import com.caldremch.http.core.*
import com.caldremch.http.core.abs.IObserverHandler
import com.caldremch.http.exception.NullDataSuccessException
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
class AbsObserver<T>(
    var callback: AbsCallback<T>?,
    var handler: IObserverHandler?,
    private val dialogEvent: IDialogHandle?,
    private val showDialog:Boolean,
    private val dialogTips:String,
    private val requestHandleEvent: IRequestHandle?,
) : Observer<T> {

    init {
        handler?.onStart()
        if(showDialog){
            dialogEvent?.dialogShowTiming(dialogTips)
        }
    }
    private class DisposableAdapter(private val d: Disposable) : IRequestContext{
        override fun cancel() {
            if(d.isDisposed.not()){
                d.dispose()
            }
        }
    }

    override fun onSubscribe(d: Disposable) {
        requestHandleEvent?.onRequestHandle(DisposableAdapter(d))
    }

    override fun onNext(t: T) {
        handler?.onSuccess(t)
        callback?.onSuccess(t)
        if (showDialog) {
            dialogEvent?.dialogDismissTiming()
        }
        handler?.onEnd()
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
            return
        }

        callback?.onError(e)
        handler?.onError(e)
        handler?.onEnd()
    }


    override fun onComplete() {
    }

}