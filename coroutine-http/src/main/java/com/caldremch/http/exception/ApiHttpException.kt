package com.caldremch.http.exception

/**
 *
 * @author Caldremch
 * @date 2019/2/26
 * @Email caldremch@163.com
 * @describe
 */
open class ApiHttpException(val code: Int, message: String?) : RuntimeException(message)