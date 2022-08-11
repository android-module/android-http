package com.caldremch.android.http.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.caldremch.http.core.deprecated.observer.IHandleListener

open class HttpViewModelPageLoad(eventListener: IHandleListener?) : HttpViewModel() {

    protected val _loadDataSuccess = MutableLiveData<Boolean>()
    val loadDataSuccess: LiveData<Boolean>
        get() = _loadDataSuccess

}