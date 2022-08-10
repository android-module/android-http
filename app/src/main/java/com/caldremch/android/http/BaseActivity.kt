package com.caldremch.android.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caldremch.http.core.RequestContextComposite
import kotlin.reflect.KClass

/**
 * Created by Leon on 2022/8/8.
 */
open abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    private lateinit var viewModel: VM

    private val requestContextComposite by lazy { RequestContextComposite() }

    abstract fun getVMClass(): KClass<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.dialogEvent.observe(this){

        }

        viewModel.requestContext.observe(this){
            requestContextComposite.add(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requestContextComposite.clear()
    }

}