//package impl
//
//import com.caldremch.android.log.errorLog
//import com.caldremch.http.core.abs.IConvertStrategy
//import com.caldremch.http.core.execption.NullDataSuccessException
//import com.caldremch.http.core.framework.base.IBaseResp
//import com.caldremch.http.core.model.DefaultBaseResp
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonNull
//import com.google.gson.JsonParseException
//import com.google.gson.JsonParser
//import java.io.File
//import java.io.InputStream
//
///**
// * Created by Leon on 2022/8/11.
// */
//class ConvertStrategyImpl : IConvertStrategy {
//
//    private val mGson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
//    private val mParser = JsonParser()
//
//    override fun <T> isStreamConvert(clz: Class<T>): Boolean {
//        return clz == File::class.java
//    }
//
//    override fun <T> convertStream(inputStream: InputStream?): T {
//        return File("") as T
//    }
//
//    //有任何异常, 直接抛出来
//    override fun <T> convertCommon(bodyString: String, clz: Class<T>): IBaseResp<T> {
//        var ret: IBaseResp<T>? = null
//        try {
//            //这里的数据结构要根据后台的来处理具体的字段类型, 不做兼容, 后台一定要规范
//            val jsonObject = mParser.parse(bodyString).asJsonObject
//            val dataJson = jsonObject["data"]
//            val code = jsonObject["code"]?.asInt
//            val msg = jsonObject["msg"]?.asString
//            if (0 != code) {
//                throw RuntimeException(msg)
//            }
//            if ((dataJson == null || dataJson is JsonNull)) {
//                throw NullDataSuccessException()
//            }
//            try {
//                if (clz == Boolean::class.java) {
//                    ret = DefaultBaseResp(dataJson.asBoolean as T, msg, code)
//                } else if (clz == Int::class.java) {
//                    ret = DefaultBaseResp(dataJson.asInt as T, msg, code)
//                } else if (clz == String::class.java) {
//                    ret = DefaultBaseResp(dataJson.toString() as T, msg, code)
//                }
//            } catch (e: Exception) {
//                throw e
//            }
//
//
//            if(ret == null){
//                if (dataJson == null || dataJson is JsonNull){
//                    ret = DefaultBaseResp(null, msg, code)
//                }else{
//                    val dataStr = dataJson.toString()
//                    if (dataJson.isJsonArray.not()) {
//                        ret =  DefaultBaseResp(mGson.fromJson(dataStr, clz), msg, code)
//                    } else {
//                        ret =  DefaultBaseResp(mGson.fromJson(dataStr, clz), msg, code)
//                    }
//                }
//            }
//
//        } catch (e: JsonParseException) {
//            errorLog { "JsonParseException" }
//            if (clz == String::class.java) {
//                errorLog { "when JsonParseException and the request class type is String , return as String" }
//                ret =  DefaultBaseResp(bodyString as T, e.message, -1)
//            }else{
//                throw e
//            }
//        }
//        return ret?:error("un know error")
//    }
//
//}
