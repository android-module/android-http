package com.caldremch.http.exception

/**
 * Created by Leon on 2022/7/8
 */
class StringCodeException(val bizCode: String, message: String?) : ApiHttpException(ApiCode.ERROR_STRING_CODE, message+"(${bizCode})") {
}