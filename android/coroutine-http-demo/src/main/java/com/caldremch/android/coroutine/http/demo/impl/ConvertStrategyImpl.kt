package com.caldremch.android.coroutine.http.demo.impl

import com.caldremch.android.log.errorLog
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.abs.IConvertStrategy
import com.caldremch.http.core.execption.ApiHttpException
import com.caldremch.http.core.framework.base.IBaseResp
import com.google.gson.GsonBuilder
import com.google.gson.JsonNull
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import java.io.File
import java.io.InputStream
import java.lang.reflect.Type

/**
 * Created by Leon on 2022/8/11.
 */
class ConvertStrategyImpl : IConvertStrategy {

    private val mGson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
    private val mParser = JsonParser()
    private val baseRespFactory=HttpInitializer.getBaseRespFactory()

    override fun <T> isStreamConvert(clz: Type): Boolean {
        return clz == File::class.java
    }

    override fun <T> convertStream(inputStream: InputStream?): T {
        return File("") as T
    }

    //有任何异常, 直接抛出来
    override fun <T> convertCommon(bodyString: String, clz: Type): IBaseResp<T> {
        var ret: IBaseResp<T>? = null
        try {
            //这里的数据结构要根据后台的来处理具体的字段类型, 不做兼容, 后台一定要规范
            val jsonObject = mParser.parse(bodyString).asJsonObject
            val dataJson = jsonObject["data"]
            val code = jsonObject["errorCode"]?.asInt
            val msg = jsonObject["errorMsg"]?.asString

            if (code != null && 0 != code) {
                throw ApiHttpException(code, msg)
            }

            if ((dataJson == null || dataJson is JsonNull)) {
                ret = baseRespFactory.create(null,  msg, code)
            } else {

                try {
                    if (clz == Boolean::class.java) {
                        ret = baseRespFactory.create(dataJson.asBoolean as T, msg, code)
                    } else if (clz == Int::class.java) {
                        ret = baseRespFactory.create(dataJson.asInt as T, msg, code)
                    } else if (clz == String::class.java) {
                        ret = baseRespFactory.create(dataJson.toString() as T, msg, code)
                    }
                } catch (e: Exception) {
                    throw e
                }

                if (ret == null) {
                    val dataStr = dataJson.toString()
                    if (dataJson.isJsonArray.not()) {
                        ret = baseRespFactory.create(mGson.fromJson(dataStr, clz), msg, code)
                    } else {
                        ret = baseRespFactory.create(mGson.fromJson(dataStr, clz), msg, code)
                    }
                }
            }


        } catch (e: JsonParseException) {
            errorLog { "JsonParseException" }
            if (clz == String::class.java) {
                errorLog { "when JsonParseException and the request class type is String , return as String" }
                ret = baseRespFactory.create(bodyString as T, e.message, -1)
            } else {
                throw e
            }
        }
        return ret ?: error("un know error")
    }

}
