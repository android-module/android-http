package com.caldremch.http.core.framework.handle

import com.caldremch.http.core.framework.handle.IRequestContext

/**
 * Created by Leon on 2022/8/8.
 */
interface IRequestHandle {
    fun onRequestHandle(ctx: IRequestContext)
}