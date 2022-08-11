package com.caldremch.android.http.viewmodel.ext

import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.android.log.debugLog

/**
 * Created by Leon on 2022/7/24.
 */
fun <VM : HttpViewModel> BaseHttpViewModelFragment<VM>.httpViewModels(): Lazy<VM> {
    val ownerProducer: () -> ViewModelStoreOwner = { this }
    val factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                debugLog { "当前的modelClass名字: ${modelClass.name}" }
                val constructor = modelClass.constructors[0]
                val realViewModel = constructor.newInstance() as T
                return realViewModel
            }
        }
    }
    return createViewModelLazy(
        getVMClass(),
        { requireActivity().viewModelStore },
        factoryProducer = factoryProducer
    )
}

fun <VM : HttpViewModel> BaseHttpViewModelActivity<VM>.httpViewModels(): Lazy<VM> {

    val httpFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val constructor = modelClass.constructors[0]
            val realViewModel = constructor.newInstance() as T
            return realViewModel
        }
    }

    val factoryPromise = {
        httpFactory
    }
    return ViewModelLazy(getVMClass(), { viewModelStore }, factoryPromise)
}