
package com.caldremch.android.http.demo

import com.caldremch.http.core.abs.IHeader


/**
 * Created by Leon on 2022/7/6
 */
class HttpCommonHeaderImpl : IHeader {
    override fun getCommonHeader(): Map<String, String> {
        val map = hashMapOf<String, String>()
        return map
    }
}