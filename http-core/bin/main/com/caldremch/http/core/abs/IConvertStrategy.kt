package com.caldremch.http.core.abs

import java.io.InputStream

/**
 * Created by Leon on 2022/7/5
 */
interface IConvertStrategy{
    fun <T> isStreamConvert(clz: Class<T>):Boolean
    fun <T> convertStream(inputStream: InputStream?):T
    fun <T> convertCommon(bodyString:String, clz: Class<T>):T
}