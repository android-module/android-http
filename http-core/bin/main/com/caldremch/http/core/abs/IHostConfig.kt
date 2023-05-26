package com.caldremch.http.core.abs

/**
 * Created by Leon on 2022/10/7
 *
 *
 */
interface IHostConfig {
    /**
     * 是否可以配置服务器url,
     * 打开配置后, currentUrl 实时生效, 否则不生效. 默认取defaultUrl
     */
    fun enableConfig(): Boolean

    /**
     * 设置当前的服务器请求 url
     */
    fun currentUrl(): String

    /**
     * 设置默认的
     */
    fun defaultUrl(): String
}