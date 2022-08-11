package com.caldremch.android.http

import android.view.View
import com.caldremch.android.http.viewmodel.ext.BaseHttpViewModelActivity
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.exec
import kotlin.reflect.KClass

class HttpActivity : BaseHttpViewModelActivity<MainViewModel>() {
    override fun getVMClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }


    override val layoutId: Int
        get() = R.layout.activity_main

    fun Go(view: View) {
        HttpManager.post("").put(Any()).exec<Any> {  }
    }

}

