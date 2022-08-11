package com.caldremch.android.http.viewmodel.ext

import android.os.Bundle
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.common.ext.BaseFragment
import com.caldremch.http.core.framework.RequestContextComposite
import kotlin.reflect.KClass


/**
 * Created by Leon on 2022/7/10
 */
abstract class BaseHttpViewModelFragment<VM : HttpViewModel> : BaseFragment() {

    protected val viewModel: VM by httpViewModels()

    private val requestContextComposite by lazy { RequestContextComposite() }

    abstract fun getVMClass(): KClass<VM>

    private fun initViewModelWithin() {
        viewModel.dialogEvent.observe(this){

        }
        viewModel.requestContext.observe(this){
            requestContextComposite.add(it)
        }
    }

    open fun initViewModel() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelWithin()
        initViewModel()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        requestContextComposite.clear()
    }
}




