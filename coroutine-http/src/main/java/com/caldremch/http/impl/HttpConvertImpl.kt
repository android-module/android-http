package com.caldremch.http.impl


import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.model.ResponseBodyWrapper
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by Leon on 2022/7/5
 */
internal class HttpConvertImpl : IConvert<ResponseBody> {

    private val convertStrategy:IConvertStrategy by inject(IConvertStrategy::class.java)

    override fun <T> convert(
        responseBodyWrapper: ResponseBodyWrapper<ResponseBody>, clz: Class<T>
    ): T {
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