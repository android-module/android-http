package com.caldremch.http.impl


import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.framework.base.IBaseResp
import com.caldremch.http.core.model.ResponseBodyWrapper
import okhttp3.ResponseBody


/**
 * Created by Leon on 2022/7/5
 */
class HttpConvertImpl : IConvert<ResponseBody> {

    private val convertStrategy  = HttpInitializer.getConvertStrategy();


    override fun <T> convert(
        responseBodyWrapper: ResponseBodyWrapper<ResponseBody>, clz: Class<T>
    ): IBaseResp<T> {
        val responseBody = responseBodyWrapper.responseBody

        if(convertStrategy.isStreamConvert(clz)){
            return convertStrategy.convertStream(responseBody.byteStream())
        }
        responseBody.use {
            val jsonRespStr: String = responseBody.string()
            return convertStrategy.convertCommon(jsonRespStr, clz)
        }
    }

}
