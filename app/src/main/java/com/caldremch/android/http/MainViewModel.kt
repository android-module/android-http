package com.caldremch.android.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestContext
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.ext.exec

/**
 * Created by Leon on 2022/8/8.
 */
class MainViewModel : HttpViewModel() {
    fun getData() {
        HttpManager.post("url")
            .bindDialogHandle(this)
            .bindRequestHandle(this)
            .showDialog("Loading...")
            .exec<Any> { }
    }

}