package com.caldremch.http.core.framework.base

/**
 * @author Leon
 * @date 2023/2/15
 * @desc
 */
interface IBaseResp<T> {
    fun getData(): T?
    fun getErrorMsg(): String?
    fun getCode(): Int?
    fun getCodeString(): String? = null
    fun getTime():Long? = null
    fun isSuccess():Boolean
}

