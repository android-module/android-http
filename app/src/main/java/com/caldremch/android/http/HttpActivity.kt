package com.caldremch.android.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.exec
import kotlin.reflect.KClass

class HttpActivity : BaseActivity<MainViewModel>() {
    override fun getVMClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HttpManager.post("").put(Any()).exec<Any> {  }
    }
}