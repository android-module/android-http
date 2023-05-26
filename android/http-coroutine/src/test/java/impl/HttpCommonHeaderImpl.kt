
package impl

import com.caldremch.http.core.abs.IHeader


/**
 * Created by Leon on 2022/7/6
 */
class HttpCommonHeaderImpl : IHeader {
    override fun getCommonHeader(): Map<String, String> {
        val map = hashMapOf("key1" to "value1", "key2" to "value2")
        return map
    }
}
