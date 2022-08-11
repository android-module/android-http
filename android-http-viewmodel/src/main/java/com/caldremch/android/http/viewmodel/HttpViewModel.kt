package com.caldremch.android.http.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestContext
import com.caldremch.http.core.framework.handle.IRequestHandle

open class HttpViewModel() : ViewModel(), IDialogHandle, IRequestHandle {
    private val _dialogEvent = MutableLiveData<Boolean>()
    val dialogEvent: LiveData<Boolean>
        get() = _dialogEvent

    private val _requestContext = MutableLiveData<IRequestContext>()
    val requestContext: LiveData<IRequestContext>
        get() = _requestContext


    override fun dialogShowTiming(dialogTips: String) {
        _dialogEvent.postValue(true)
    }

    override fun dialogDismissTiming() {
        _dialogEvent.postValue(false)
    }

    override fun onRequestHandle(ctx: IRequestContext) {
        _requestContext.postValue(ctx)
    }

}