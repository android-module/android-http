package com.caldremch.http.core.framework.base

/**
 * Created by Leon on 2022/10/30.
 */
interface IPostRequest<R : IRequest<R>> : IRequest<R> {
    fun  /* renamed from: put */put(obj: Any): R
    fun  /* renamed from: formUrlEncoded */formUrlEncoded(): R
    fun  /* renamed from: postQuery */postQuery(): R
}
