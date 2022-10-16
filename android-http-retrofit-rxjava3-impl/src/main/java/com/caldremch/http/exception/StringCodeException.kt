package com.caldremch.http.exception

import com.caldremch.http.core.execption.ApiCode
import com.caldremch.http.core.execption.ApiHttpException

/**
 * Created by Leon on 2022/7/8
 */
internal class StringCodeException(val bizCode: String, message: String?) : ApiHttpException(ApiCode.ERROR_STRING_CODE, message+"(${bizCode})") {
}