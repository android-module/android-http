package com.caldremch.android.coroutine.http.demo.impl

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.exception.ApiHttpException

/**
 * Created by Leon on 2022/7/5
 */
class HttpObsHandlerImpl : ICommonRequestEventCallback {

    override fun onError(e: Throwable, showToast: Boolean) {
        debugLog { "走到这里吗? ${e.message}" }
    }
}