package com.caldremch.android.http.viewmodel.ext

import android.os.Bundle
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.common.ext.BaseActivity
import com.caldremch.http.core.framework.RequestContextComposite
import kotlin.reflect.KClass
/**
 * Created by Leon on 2022/7/24.
 */
abstract class BaseHttpViewModelActivity<VM : HttpViewModel> : BaseActivity(){

    protected val viewModel: VM by httpViewModels()

    private val requestContextComposite by lazy { RequestContextComposite() }

    private fun initViewModelWithin() {
        viewModel.dialogEvent.observe(this){

        }
        viewModel.requestContext.observe(this){
            requestContextComposite.add(it)
        }
    }
    open fun initViewModel() {

    }
    abstract fun getVMClass(): KClass<VM>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelWithin()
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        requestContextComposite.clear()
    }

}