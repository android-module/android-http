package com.caldremch.android.coroutine.http.demo.biz

/**
 * Created by Leon on 2022/7/8
 */
class CustomException(val bizCode: String, message: String?) : RuntimeException(message)