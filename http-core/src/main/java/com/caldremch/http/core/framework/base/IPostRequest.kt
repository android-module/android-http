package com.caldremch.http.core.framework.base

/**
 *
 * @author Caldremch
 *
 * @date 2020-09-08
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
interface IPostRequest<out R : IRequest<R>> : IRequest<R> {
    fun put(body: Any): R
    fun formUrlEncoded(): R
    fun postQuery(): R
}