package com.caldremch.android.http.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.deprecated.observer.IHandleListener
import com.caldremch.http.core.deprecated.observer.IHttpEventListenerProvider
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

/**
 * Created by Leon on 2022/7/10
 */


interface RepositoryProviderFactory {
    fun <T : HttpRepository> create(modelClass: Class<T>): T
}

class RepositoryLazy<R : HttpRepository>(
    private val repoClass: KClass<R>,
    private val factoryProducer: () -> RepositoryProviderFactory
) : Lazy<R> {
    private var cached: R? = null
    override val value: R
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = factoryProducer()
                factory.create(repoClass.java).also {
                    cached = it
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized(): Boolean = cached != null
}


inline fun <reified Repository : HttpRepository> HttpViewModel.repositorys(
    noinline httpEventProducer: (() -> IHttpEventListenerProvider?) = { this },
    noinline factoryProducer: (() -> RepositoryProviderFactory)? = null,

    ): Lazy<Repository> {

    val defaultFactory = factoryProducer ?: {
        object : RepositoryProviderFactory {
            override fun <T : HttpRepository> create(modelClass: Class<T>): T {
                debugLog { "当前的modelClass名字: ${modelClass.name}" }
                Log.d("wlwwl", "create: ${modelClass.name}")
                val constructs = modelClass.constructors
                //默认获取第一个
                val correctConstruct = constructs[0];
                val repo = correctConstruct.newInstance(httpEventProducer.invoke()?.provider()) as T

                return repo
            }
        }
    }
    return RepositoryLazy(Repository::class, defaultFactory)
}

open class HttpViewModelPageLoad(eventListener: IHandleListener?) : HttpViewModel(eventListener) {

    protected val _loadDataSuccess = MutableLiveData<Boolean>()
    val loadDataSuccess: LiveData<Boolean>
        get() = _loadDataSuccess

}

open class HttpViewModel(eventListener: IHandleListener?) : ViewModel(),
    IHttpEventListenerProvider {

    private val weak  = WeakReference<IHandleListener>(eventListener)



    override fun onCleared() {
        super.onCleared()
        /**
         * 取消这个ViewModel的网络请求, 假如是RxJava, 那么最终的实例, 是在Observer里面,也就是AbsObserver, 那么是如何获取这个AbsObserver的接口实例?,
         * ViewModel不具备取消的能力, 所以需要通过Repository来取消, 那么先需要通知Repository要取消网络请求, 取消所有当前的网路请求
         */

    }

    override fun provider(): IHandleListener? {
        return weak.get()
    }

}