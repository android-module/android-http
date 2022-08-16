package com.caldremch.android.http

import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.fullFutureTaskExec
import com.caldremch.http.core.ext.postFullFutureTask
import com.caldremch.http.core.framework.base.IFullFutureTask

/**
 * Created by Leon on 2022/8/8.
 */

class MainRepository {
    fun getData(): IFullFutureTask<Any> {
        return HttpManager.post("url")
            .postFullFutureTask()
    }
}

class MainViewModel : HttpViewModel() {

    private val repository by lazy { MainRepository() }

    fun getData() {
        repository
            .getData()
            .bindDialogHandle(this)
            .bindRequestHandle(this)
            .showDialog()
            .disableToast()
            .fullFutureTaskExec {
            }
    }

}