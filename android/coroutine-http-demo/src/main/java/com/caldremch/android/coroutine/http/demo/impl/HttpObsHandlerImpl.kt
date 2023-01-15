package com.caldremch.android.coroutine.http.demo.impl

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.exception.ApiHttpException

/**
 * Created by Leon on 2022/7/5
 */
class HttpObsHandlerImpl : ICommonRequestEventCallback {
    override fun onStart() {
        debugLog { "onStart" }
    }

    override fun onSuccess(obj: Any?) {
        debugLog { "onSuccess" }
    }

    override fun onError(e: Throwable, showToast: Boolean) {
        debugLog { "onError :${e.message}" }
    }

    override fun onEnd() {
        debugLog { "onEnd" }
    }
}
