package com.caldremch.android.http.demo

import com.caldremch.http.core.abs.IServerUrlConfig


/**
 * Created by Leon on 2022/7/5
 */
class HttpUrlConfigImpl : IServerUrlConfig {

    override fun enableConfig(): Boolean {
        return true
    }

    override fun currentUrl(): String {
        return "https://baidu.com/"
//        return "https://google.com/"  //timeout
    }

    override fun defaultUrl(): String {
        return "https:///baidu.com/"
        //        return "https://google.com/"  //timeout
    }
}