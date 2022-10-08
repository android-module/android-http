package com.caldremch.android.http

import android.app.Application
import com.caldremch.android.log.DebugLogInitializer

/**
 * Created by Leon on 2022/10/7.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DebugLogInitializer.initWithDetect(true)
    }
}