package com.caldremch.http.execute

import com.caldremch.android.http.Api
import com.caldremch.http.AbsObserver
import com.caldremch.http.JavaRxTransform
import com.caldremch.http.RequestHelper
import com.caldremch.http.core.AbsCallback
import com.caldremch.http.core.IDialogHandle
import com.caldremch.http.core.ILifecycleObserver
import com.caldremch.http.core.IRequestHandle
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.abs.IObserverHandler
import com.caldremch.http.core.abs.IServerUrlConfig
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent

/**
 * Created by Leon on 2022/7/8
 */

abstract class BaseExecute {


    val noCustomerHeaderApi: Api
        get() =  RequestHelper(false).getApi()
    val api: Api
        get() = if (serverUrlConfig.enableConfig()) RequestHelper().getApi() else RequestHelper.INSTANCE.getApi()
    private val serverUrlConfig: IServerUrlConfig by KoinJavaComponent.inject(IServerUrlConfig::class.java)
    private val convert: IConvert<ResponseBody> by KoinJavaComponent.inject(IConvert::class.java)
    private val obsHandler: IObserverHandler by KoinJavaComponent.inject(IObserverHandler::class.java)



    fun <T> go(
        obs: Observable<ResponseBody>,
        callback: AbsCallback<T>,
        clazz: Class<T>,
        dialogEvent: IDialogHandle?,
        showDialog:Boolean,
        dialogTips:String,
        requestHandleEvent: IRequestHandle?,
    ) {
        val observer = AbsObserver(callback, obsHandler, dialogEvent, showDialog, dialogTips, requestHandleEvent)
        obs.compose(JavaRxTransform.transformer(clazz, convert)).subscribe(observer)
    }

}