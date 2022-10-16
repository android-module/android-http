package com.caldremch.android.http

import android.app.Application
import android.content.Context
import com.caldremch.android.http.demo.HttpCommonHeaderImpl
import com.caldremch.android.http.demo.HttpObsHandlerImpl
import com.caldremch.android.http.demo.HttpUrlConfigImpl
import com.caldremch.android.http.viewmodel.ext.IHttpDialogEvent
import com.caldremch.android.log.DebugLogInitializer
import com.caldremch.http.core.HttpManagerInitializer
import com.caldremch.http.core.IHttpInit
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.abs.IConvertStrategy
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IServerUrlConfig
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Leon on 2022/10/7.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DebugLogInitializer.initWithDetect(true)
        HttpManagerInitializer.init(MyHttpInit())
    }
}