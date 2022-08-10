package com.caldremch.android.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.IHttpDialogEvent
import com.caldremch.http.core.ext.exec

/**
 * Created by Leon on 2022/8/8.
 */

open class BaseHttpViewModel : ViewModel(), IHttpDialogEvent {

    val _dialogEvent = MutableLiveData<Boolean>()
    val dialogEvent: LiveData<Boolean>
        get() = _dialogEvent

    override fun dialogShowTiming() {
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
}

class MainViewModel : BaseHttpViewModel() {

    fun getData() {

    }

    override fun onCleared() {
        super.onCleared()
        val a = 1;
        debugLog { "onCleared ${a}" }

        HttpManager.post("url")
            .showDialog(this)
            .exec<Any> { }
    }

}