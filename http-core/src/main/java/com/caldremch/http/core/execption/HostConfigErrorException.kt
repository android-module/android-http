package com.caldremch.http.core.execption

/**
 * Created by Leon on 2022/10/7
 */
class HostConfigErrorException(message:String = "at least config one host"): RuntimeException(message) {
}