package com.caldremch.http.impl


import com.caldremch.android.log.errorLog
import com.caldremch.http.HttpConvertUtils
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.model.ResponseBodyWrapper
import com.caldremch.http.exception.NullDataSuccessException
import com.google.gson.GsonBuilder
import com.google.gson.JsonNull
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import java.io.File

/**
 * Created by Leon on 2022/7/5
 */
class HttpConvertImpl : IConvert<ResponseBody> {

    private val mGson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
    private val mParser = JsonParser()



    override fun <T> convert(
        responseBodyWrapper: ResponseBodyWrapper<ResponseBody>, clz: Class<T>
    ): T {
        val responseBody = responseBodyWrapper.responseBody

        val jsonRespStr: String = responseBody.string()
        responseBody.use {
            try {
                val jsonObject = mParser.parse(jsonRespStr).asJsonObject
                val dataJson = jsonObject["data"]
                val code = jsonObject["code"]
                if (code != null) {
                    val codeString = code.asString
                    var msg: String? = ""
                    if (jsonObject["msg"] != null) {
                        msg = jsonObject["msg"].asString
                    }
                    if ("0000" != codeString) {
//                        throw CustomException(codeString, msg)
                    }

                    if ((dataJson == null || dataJson is JsonNull)) {
                        throw NullDataSuccessException()
                    }

                    try {
                        if (clz == Boolean::class.java) {
                            return dataJson.asBoolean as T
                        }
                        if (clz == Int::class.java) {
                            return dataJson.asInt as T
                        }
                        if (clz == String::class.java) {
                            return dataJson.toString() as T
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                    if (dataJson.isJsonArray.not()) {
                        val dataJsonObj = dataJson.asJsonObject
                        val status = dataJsonObj["status"]?.asInt
                        if (status != null && status != 200) {
                            val dataError = dataJsonObj["error"]?.asString
                            val dataMessage = dataJsonObj["message"]?.asString
//                            throw CustomException(codeString, "$status $dataError $dataMessage")
                        }
                    }

                }

                if (dataJson == null || dataJson is JsonNull) throw NullDataSuccessException()

                if (dataJson.isJsonArray.not()) {
                    val dataStr = dataJson.toString()
                    return mGson.fromJson(dataStr, clz)
                } else {
                    val dataStr = dataJson.toString()
                    return mGson.fromJson(dataStr, clz)
                }
            } catch (e: JsonParseException) {
                errorLog { "JsonParseException" }
                if (clz == String::class.java) {
                    errorLog { "when JsonParseException and the request class type is String , return as String" }
                    return jsonRespStr as T
                }
                throw e
            }

        }
    }

}