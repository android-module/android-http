package com.caldremch.http.impl

import com.caldremch.http.core.IRequestInstanceAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by Leon on 2022/8/8.
 */
class RequestInstanceAdapterImpl : IRequestInstanceAdapter {

    val set = HashSet<IRequestInstanceAdapter.ICancelTag>()
    private val compositeDisposable = CompositeDisposable()

    override fun manager(instance: IRequestInstanceAdapter.ICancelTag) {
        set.add(instance)
    }

    override fun cancel() {
       set.forEach {
           it.cancel()
       }
    }
}