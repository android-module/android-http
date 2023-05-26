package com.caldremch.http.core.framework.ktx

import com.caldremch.http.core.framework.base.IBaseResp

/**
 * @author Leon
 * @date 2023/2/18
 * @desc
 */
fun <T> IBaseResp<T>.isSuccessAndDataNotNull():Boolean{
    return isSuccess() && getData() != null
}

fun <T> IBaseResp<T>.requireData():T{
    return getData()?: error("The data is Null")
}
