package com.caldremch.android.http

import androidx.lifecycle.*
import com.caldremch.android.http.viewmodel.HttpViewModel

/**
 * Created by Leon on 2022/8/9.
 */


private fun handleLifeCycleCancel(lifecycle: Lifecycle, viewModel: com.caldremch.android.http.viewmodel.HttpViewModel) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            viewModel.onCancel()
        }
    })
}

fun <VM : com.caldremch.android.http.viewmodel.HttpViewModel> BaseActivity<VM>.httpViewModels(): Lazy<VM> {


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