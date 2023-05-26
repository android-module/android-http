package com.caldremch.http.core.framework.base

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
interface IBaseRespFactory {
    fun <T> create(
        data: T?,
        errorMsg: String? ,
        code: Int? ,
    ): IBaseResp<T> = create(data, code, errorMsg, null)

    fun <T> create(
        data: T?,
        code: Int? ,
        errorMsg: String? ,
        time: Long? ,
        ): IBaseResp<T> = create(data, code, errorMsg, time, null)


    fun <T> create(
        data: T?,
        code: Int? ,
        errorMsg: String? ,
        time: Long? ,
        codeString: String? ,
    ): IBaseResp<T>
}
