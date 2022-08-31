package com.caldremch.http.core.abs

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-10 11:40
 *
 * @email caldremch@163.com
 *
 * @describe 服务器 url 配置, 更具用户端实现具体配置
 *
 **/
interface IServerUrlConfig {

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
     * 设置默认的 url
     */
    fun defaultUrl(): String

}