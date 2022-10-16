package com.caldremch.android.http.coroutine

import com.caldremch.http.core.IHttpInit
import com.caldremch.http.koinHttpModules
import org.koin.core.module.Module

/**
 * Created by Leon on 2022/10/16.
 */
class CoroutineHttpInitImpl : IHttpInit {
    override fun onLoaderCreate(): Module {
        return koinHttpModules
    }
}