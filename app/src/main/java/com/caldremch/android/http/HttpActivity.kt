package com.caldremch.android.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.exec
import kotlin.reflect.KClass

class HttpActivity : BaseActivity<MainViewModel>() {
    override fun getVMClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }
}