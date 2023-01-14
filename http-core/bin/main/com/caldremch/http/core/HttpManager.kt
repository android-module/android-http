package com.caldremch.http.core

import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.PostRequest

/**
 * Created by Leon on 2022/7/7
 */
object HttpManager{

    fun get(url: String): GetRequest {
        return GetRequest(url)
    }

    fun post(url: String): PostRequest {
        return PostRequest(url)
    }
}