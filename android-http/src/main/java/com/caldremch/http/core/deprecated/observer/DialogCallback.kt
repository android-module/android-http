package com.caldremch.http.core.deprecated.observer

import com.caldremch.http.core.framework.base.HttpCallback

/**
 * Created by Leon on 2022/7/10
 */
@Deprecated(message = "use DialogEventHandle")
abstract class DialogCallback<T>(var tips: String? = "loading...") : HttpCallback<T> {

}