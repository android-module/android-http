package com.caldremch.http

import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.model.ResponseBodyWrapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 * Created by Leon on 2022/7/8
 */
internal object JavaRxTransform {
    fun <R> transformer(
        clazz: Class<R>,
        convert: IConvert<ResponseBody>
    ): ObservableTransformer<ResponseBody, R> {
        return ObservableTransformer { upstream ->
            upstream.flatMap { responseBody ->
                Observable.create { emitter ->
                    emitter.onNext(
                        convert.convert(
                            ResponseBodyWrapper(responseBody),
                            clazz
                        )
                    )
                }
            }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}