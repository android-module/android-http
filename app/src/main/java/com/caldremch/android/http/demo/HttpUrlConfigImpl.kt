package com.caldremch.android.http.demo

import com.caldremch.http.core.abs.IHostConfig
import com.caldremch.http.core.abs.IServerUrlConfig


/**
 * Created by Leon on 2022/7/5
 */
class HttpUrlConfigImpl : IServerUrlConfig {

    class DefaultHost : IHostConfig {
        override fun enableConfig(): Boolean {
            return true
        }

        override fun currentUrl(): String {
            return "http://baidu.com/"
        }

        override fun defaultUrl(): String {
            return "http://baidu.com/"
        }

    }

    class SecondaryHost : IHostConfig {
        override fun enableConfig(): Boolean {
            return true
        }

        override fun currentUrl(): String {
            return "http://google.com/"
        }

        override fun defaultUrl(): String {
            return "http://google.com/"
        }

    }


    private val channels by lazy {
        hashMapOf<Any?, IHostConfig>(
            null to DefaultHost(),
            1 to SecondaryHost()
        )
    }


    override fun channels(): MutableMap<Any?, IHostConfig> {
        return channels
    }
}