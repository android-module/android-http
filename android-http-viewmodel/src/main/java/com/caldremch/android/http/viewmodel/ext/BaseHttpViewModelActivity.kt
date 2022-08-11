package com.caldremch.android.http.viewmodel.ext

import android.os.Bundle
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.android.log.debugLog
import com.caldremch.common.ext.BaseActivity
import com.caldremch.http.core.framework.RequestContextComposite
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass
/**
 * Created by Leon on 2022/7/24.
 */
abstract class BaseHttpViewModelActivity<VM : HttpViewModel> : BaseActivity(){

    protected val viewModel: VM by httpViewModels()

    private val httpDialogEvent: IHttpDialogEvent by inject { parametersOf(this) }

    private val requestContextComposite by lazy { RequestContextComposite() }

    private fun initViewModelWithin() {
        viewModel.dialogEvent.observe(this){
            if(it){
                httpDialogEvent.dialogShowTiming()
            }else{
                httpDialogEvent.dialogDismissTiming()
            }
        }
        viewModel.requestContext.observe(this){
            debugLog { "添加请求实例" }
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