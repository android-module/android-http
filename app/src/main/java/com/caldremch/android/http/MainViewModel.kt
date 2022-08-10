package com.caldremch.android.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.IDialogHandle
import com.caldremch.http.core.IRequestContext
import com.caldremch.http.core.IRequestHandle
import com.caldremch.http.core.ext.exec

/**
 * Created by Leon on 2022/8/8.
 */

open class BaseViewModel : ViewModel(), IDialogHandle, IRequestHandle {

   private val _dialogEvent = MutableLiveData<Boolean>()
    val dialogEvent: LiveData<Boolean>
        get() = _dialogEvent

    private  val _requestContext = MutableLiveData<IRequestContext>()
    val requestContext: LiveData<IRequestContext>
        get() = _requestContext

    override fun dialogShowTiming(dialogTips: String) {
        _dialogEvent.postValue(true)
    }

    override fun dialogDismissTiming() {
        _dialogEvent.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        val a = 1;
        debugLog { "onCleared  ${a}" }
    }

    override fun onRequestHandle(ctx: IRequestContext) {
        _requestContext.postValue(ctx)
    }
}

class MainViewModel : BaseViewModel() {

    fun getData() {

    }

    override fun onCleared() {
        super.onCleared()
        val a = 1;
        debugLog { "onCleared ${a}" }
        HttpManager.post("url")
            .bindDialogHandle(this)
            .bindRequestHandle(this)
            .exec<Any> { }
    }

}