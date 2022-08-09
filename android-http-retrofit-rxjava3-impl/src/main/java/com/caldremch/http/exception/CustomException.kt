package com.caldremch.http.exception

/**
 * Created by Leon on 2022/7/8
 */
class CustomException(val bizCode: String, message: String?) : ApiHttpException(ApiCode.ERROR, message+"(${bizCode})") {

}