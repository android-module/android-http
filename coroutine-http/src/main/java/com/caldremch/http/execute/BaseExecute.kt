package com.caldremch.http.execute

import com.caldremch.http.Api
import com.caldremch.http.CoroutineHandler
import com.caldremch.http.RequestHelper
import com.caldremch.http.core.abs.*
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.exception.HostConfigErrorException
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent

/**
 * Created by Leon on 2022/7/8
 */

internal abstract class BaseExecute {

    private val serverUrlConfig: IServerUrlConfig by KoinJavaComponent.inject(IServerUrlConfig::class.java)
    protected val convert: IConvert<ResponseBody> by KoinJavaComponent.inject(IConvert::class.java)
    private val obsHandler: ICommonRequestEventCallback by KoinJavaComponent.inject(ICommonRequestEventCallback::class.java)


    private val helpersMap = hashMapOf<Any, RequestHelper>()

    private fun getUrlByChannel(channels: MutableMap<Any?, IHostConfig>, channel: Any?): String {

        if (channel == null) {
            //默认
            val hostConfig: IHostConfig = channels[null] ?: (channels.iterator().next() as IHostConfig)
            return if (hostConfig.enableConfig()) hostConfig.currentUrl() else hostConfig.defaultUrl()
        }

        val hostConfig = channels[channel] ?: throw HostConfigErrorException("channel $channel is not config")
        return if (hostConfig.enableConfig()) hostConfig.currentUrl() else hostConfig.defaultUrl()
    }

    protected fun getApi(noCustomerHeader: Boolean, channel: Any?): Api {

        val channels = serverUrlConfig.channels()
        if (channels.isEmpty()) throw HostConfigErrorException()


        if (noCustomerHeader) {
            val baseUrl = getUrlByChannel(channels, channel)
            val requestHelper = RequestHelper(false, baseUrl)
            return requestHelper.getApi()
        }

        if (channel == null) {
            //默认
            val hostConfig: IHostConfig = channels[null] ?: (channels.iterator().next() as IHostConfig)
            return if (hostConfig.enableConfig()) RequestHelper().getApi() else RequestHelper.INSTANCE.getApi()
        }

        val hostConfig = channels[channel] ?: throw HostConfigErrorException("channel $channel is not config")
        val baseUrl = if (hostConfig.enableConfig()) hostConfig.currentUrl() else hostConfig.defaultUrl()
        return if (hostConfig.enableConfig()) RequestHelper(true, baseUrl).getApi() else {
            var cacheHelper = helpersMap[channel]
            if (cacheHelper == null) {
                val newHelper = RequestHelper(true, baseUrl)
                helpersMap[channel] = newHelper
                cacheHelper = newHelper
            }
            cacheHelper.getApi()
        }

    }


    fun <T> go(
        callback: AbsCallback<T>,
        clazz: Class<T>,
        dialogEvent: IDialogHandle?,
        showDialog: Boolean,
        dialogTips: String,
        requestHandleEvent: IRequestHandle?,
        showToast: Boolean
    ): CoroutineHandler<T> {
        return CoroutineHandler(
            callback,
            obsHandler,
            dialogEvent,
            showDialog,
            dialogTips,
            requestHandleEvent,
            showToast
        )

    }

}