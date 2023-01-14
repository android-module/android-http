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
     * 这是一个应用主体, 有多个host
     */
    fun channels():MutableMap<Any?, IHostConfig>

}