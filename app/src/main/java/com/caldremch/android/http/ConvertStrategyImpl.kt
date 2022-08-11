package com.caldremch.android.http

import com.caldremch.android.log.errorLog
import com.caldremch.http.exception.NullDataSuccessException
import com.caldremch.http.impl.IConvertStrategy
import com.google.gson.GsonBuilder
import com.google.gson.JsonNull
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import java.io.File
import java.io.InputStream

/**
 * Created by Leon on 2022/8/11.
 */
class ConvertStrategyImpl : IConvertStrategy {

    private val mGson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
    private val mParser = JsonParser()

    override fun <T> isStreamConvert(clz: Class<T>): Boolean {
        return clz == File::class.java
    }

    override fun <T> convertStream(inputStream: InputStream?): T {
        return File("") as T
    }

    override fun <T> convertCommon(bodyString: String, clz: Class<T>): T {
        try {
            val jsonObject = mParser.parse(bodyString).asJsonObject
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
                return bodyString as T
            }
            throw e
        }
    }

}