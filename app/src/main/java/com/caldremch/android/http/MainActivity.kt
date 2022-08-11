package com.caldremch.android.http

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.caldremch.android.http.demo.HttpCommonHeaderImpl
import com.caldremch.android.http.demo.HttpObsHandlerImpl
import com.caldremch.android.http.demo.HttpUrlConfigImpl
import com.caldremch.android.log.DebugLogInitializer
import com.caldremch.common.utils.startActivity
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IServerUrlConfig
import com.caldremch.http.core.ext.exec
import com.caldremch.http.koinHttpModules
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DebugLogInitializer.init(true)
        startKoin {
            modules(koinHttpModules)
            modules(module {
                singleOf<IHeader> { HttpCommonHeaderImpl() }
                factoryOf<ICommonRequestEventCallback> {
                    HttpObsHandlerImpl()
                }
                singleOf<IServerUrlConfig> { HttpUrlConfigImpl() }
            })
        }
    }

    fun Go(view: View) {
        startActivity<HttpActivity>()
    }
}