package com.caldremch.android.http

import com.caldremch.android.http.ext.composeBind
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

    fun getDataWithChannel(): IFullFutureTask<Any> {
        return HttpManager.post("url")
            .channel(1)
            .postFullFutureTask()
    }
}

class MainViewModel : HttpViewModel() {
    private val repository by lazy { MainRepository() }
    fun getData() {
        composeBind(repository
            .getData())
            .disableToast()
            .fullFutureTaskExec {
            }

        composeBind(repository
            .getDataWithChannel())
            .disableToast()
            .fullFutureTaskExec {
            }
    }

}