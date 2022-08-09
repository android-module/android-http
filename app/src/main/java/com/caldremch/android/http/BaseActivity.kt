package com.caldremch.android.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.caldremch.http.core.IRequestInstanceAdapter
import com.caldremch.http.impl.RequestInstanceAdapterImpl
import kotlin.reflect.KClass

/**
 * Created by Leon on 2022/8/8.
 */
open abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    private val requestInstanceAdapter: IRequestInstanceAdapter by lazy { RequestInstanceAdapterImpl() }

    abstract fun getVMClass(): KClass<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        requestInstanceAdapter.cancel()
    }

}