package com.caldremch.http.execute

import com.caldremch.android.http.Api
import com.caldremch.http.RxJavaObserver
import com.caldremch.http.JavaRxTransform
import com.caldremch.http.RequestHelper
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.abs.IServerUrlConfig
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent

/**
 * Created by Leon on 2022/7/8
 */

internal abstract class BaseExecute {


    val noCustomerHeaderApi: Api
        get() =  RequestHelper(false).getApi()
    val api: Api
        get() = if (serverUrlConfig.enableConfig()) RequestHelper().getApi() else RequestHelper.INSTANCE.getApi()
    private val serverUrlConfig: IServerUrlConfig by KoinJavaComponent.inject(IServerUrlConfig::class.java)
    private val convert: IConvert<ResponseBody> by KoinJavaComponent.inject(IConvert::class.java)
    private val obsHandler: ICommonRequestEventCallback by KoinJavaComponent.inject(ICommonRequestEventCallback::class.java)



    fun <T> go(
        obs: Observable<ResponseBody>,
        callback: AbsCallback<T>,
        clazz: Class<T>,
        dialogEvent: IDialogHandle?,
        showDialog:Boolean,
        dialogTips:String,
        requestHandleEvent: IRequestHandle?,
        showToast:Boolean
    ) {
        val observer = RxJavaObserver(
            callback,
            obsHandler,
            dialogEvent,
            showDialog,
            dialogTips,
            requestHandleEvent,
            showToast
            )
        obs.compose(JavaRxTransform.transformer(clazz, convert)).subscribe(observer)
    }

}