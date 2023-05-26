package com.caldremch.http.adapter

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.framework.handle.IRequestContext
import kotlinx.coroutines.Job

/**
 * Created by Leon on 2022/8/30
 */
class CoroutineRequestAdapter(private val job:Job) : IRequestContext {
    override fun cancel() {
        if(job.isCancelled.not()){
            debugLog{"释放请求"}
            job.cancel()
        }
    }
}