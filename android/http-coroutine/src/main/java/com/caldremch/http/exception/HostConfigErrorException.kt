package com.caldremch.http.exception

/**
 * Created by Leon on 2022/10/7
 */
class HostConfigErrorException(message:String = "at least config one host"): RuntimeException(message) {
}