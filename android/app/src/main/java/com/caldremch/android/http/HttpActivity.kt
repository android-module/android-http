package com.caldremch.android.http

import android.view.View
import com.caldremch.android.http.viewmodel.ext.BaseHttpViewModelActivity
import kotlin.reflect.KClass

class HttpActivity : BaseHttpViewModelActivity<MainViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_http

    fun Go(view: View) {
        viewModel.getData()
    }

    fun Close(view: View) {
        finish()
    }

}

