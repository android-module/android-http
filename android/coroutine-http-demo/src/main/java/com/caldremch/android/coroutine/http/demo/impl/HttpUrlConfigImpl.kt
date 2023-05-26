package com.caldremch.android.coroutine.http.demo.impl

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
            return "https://www.wanandroid.com/"
        }

        override fun defaultUrl(): String {
            return "https://www.wanandroid.com/"
        }

    }

    class JuHeApi : IHostConfig {
        override fun enableConfig(): Boolean {
            return true
        }

        override fun currentUrl(): String {
            return "http://v.juhe.cn/"
        }

        override fun defaultUrl(): String {
            return "http://v.juhe.cn/"
        }

    }

    class GoLocalApi : IHostConfig {
        override fun enableConfig(): Boolean {
            return true
        }

        override fun currentUrl(): String {
            return "http://localhost:28092/"
        }

        override fun defaultUrl(): String {
            return "http://localhost:28092/"
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
            1 to SecondaryHost(),
            2 to JuHeApi(),
            3 to GoLocalApi()
        )
    }


    override fun channels(): MutableMap<Any?, IHostConfig> {
        return channels
    }
}
