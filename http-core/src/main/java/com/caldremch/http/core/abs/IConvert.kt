package com.caldremch.http.core.abs

import com.caldremch.http.core.model.ResponseBodyWrapper

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 15:33
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
interface IConvert<R> {
    fun <T> convert(responseBodyWrapper: ResponseBodyWrapper<R>, clz:Class<T>): T
}