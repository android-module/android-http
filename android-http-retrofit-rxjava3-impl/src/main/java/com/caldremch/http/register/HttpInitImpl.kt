package com.caldremch.http.register

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.IHttpInit
import com.caldremch.http.koinHttpModules
import org.koin.core.module.Module

/**
 * Created by Leon on 2022/10/16.
 */
class HttpInitImpl : IHttpInit {
    override fun onLoaderCreate(): Module {
        debugLog { "register android-http-retrofit-rxjava3-impl koin modules" }
        return koinHttpModules
    }
}