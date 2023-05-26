package com.caldremch.http.core.params

import java.io.File

/**
 * @author Caldremch
 * @date 2019-08-20 15:54
 * @email caldremch@163.com
 * @describe
 */
class HttpParams {
   private var urlParamsMap: MutableMap<String, Any> = mutableMapOf()

    fun getUrlParamsMap():MutableMap<String, Any> {
       return urlParamsMap
    }

    val urlParams: MutableMap<String, Any>
        get() = urlParamsMap

    fun setUrlParamsMap(urlParamsMap: MutableMap<String, Any>) {
        this.urlParamsMap = urlParamsMap
    }

    constructor() {
    }

    constructor(key: String, value: String) {
        put(key, value)
    }

    constructor(key: String, file: File) {
        put(key, file)
    }


    fun put(key: String, value: Any) {
        urlParamsMap[key] = value
    }

    val isEmpty: Boolean
        get() = urlParamsMap.isEmpty()


}