
package com.caldremch.android.coroutine.http.demo.impl

import com.caldremch.android.coroutine.http.demo.biz.UserManager
import com.caldremch.http.core.abs.IHeader


/**
 * Created by Leon on 2022/7/6
 */
class HttpCommonHeaderImpl : IHeader {
    override fun getCommonHeader(): Map<String, String> {
        val map = hashMapOf("key1" to "value1", "key2" to "value2")
        UserManager.gid?.let { map["h1"] = it }
        UserManager.token?.let { map["h2"] = it }
        return map
    }
}