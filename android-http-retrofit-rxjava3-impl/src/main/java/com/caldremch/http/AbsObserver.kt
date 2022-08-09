package com.caldremch.http

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.AbsCallback
import com.caldremch.http.core.DialogCallback
import com.caldremch.http.core.IHttpEventListener
import com.caldremch.http.core.abs.IObserverHandler
import com.caldremch.http.core.observer.HttpObservable
import com.caldremch.http.core.observer.HttpObserver
import com.caldremch.http.exception.NullDataSuccessException
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.ref.WeakReference

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
    @Deprecated(message = "delete in future maybe")
    var handler: IObserverHandler?,
    private val cancelHttpObservable: HttpObservable?
) : Observer<T> {

    private var weakReferenceListener: WeakReference<IHttpEventListener>? = null
    private var d: Disposable? = null


    private inner class CancelHttpObserver : HttpObserver {
        override fun update(o: HttpObservable?, arg: Any?) {
            weakReferenceListener?.clear()
            debugLog { "CancelHttpObserver 收到取消通知...${d?.isDisposed}" }
            d?.let {
                if (!it.isDisposed) {
                    debugLog { "CancelHttpObserver 取消网络请求..." }
                    it.dispose()
                }
            }
        }
    }

    private val observer by lazy { CancelHttpObserver() }

    init {
        debugLog { "网络请求者观察者初始化:..." }
        debugLog { "当前网络请求监听者:${cancelHttpObservable?.javaClass?.name}" }
        cancelHttpObservable?.httpEventListener?.let {
            weakReferenceListener = WeakReference(it)
        }
        cancelHttpObservable?.addObserver(observer)
        handler?.onInit()
        if (isDialogCallback()) {
            weakReferenceListener?.get()?.dialogShowTiming()
        }
    }

    /**
     * 是否是 [DialogCallback]
     */
    private fun isDialogCallback(): Boolean {
        if (callback is DialogCallback<T>) {
            return true
        }
        return false
    }


    override fun onSubscribe(d: Disposable) {
        this.d = d

        // TODO: 开始监听外部的动作, 是否触发了取消


//        handler?.onSubscribe(d)
//        callback?.onPreRequest(d)
    }

    override fun onNext(t: T) {
        handler?.onNext(t)
        callback?.onSuccess(t)
        if (isDialogCallback()) {
            weakReferenceListener?.get()?.dialogDismissTiming()
        }
        weakReferenceListener?.clear()
    }

    override fun onError(e: Throwable) {

        //close dialog
        if (isDialogCallback()) {
            weakReferenceListener?.get()?.dialogDismissTiming()
        }

        /**
         * code is 200, but null T ,[AbsCallback.onSuccess] callback
         */
        if (e is NullDataSuccessException) {
            callback?.onSuccess(null)
            return
        }

        /**
         * handle error callback
         */
        handler?.onError(e)
        callback?.onError(e)
        weakReferenceListener?.clear()
    }


    override fun onComplete() {
        handler?.onComplete()

    }

}