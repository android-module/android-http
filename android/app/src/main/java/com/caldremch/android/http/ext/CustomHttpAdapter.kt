package com.caldremch.android.http.ext

import com.caldremch.android.http.adapter.HttpViewModel
import com.caldremch.android.http.adapter.IRequestContextAdapter
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestContext
import com.caldremch.http.core.framework.handle.IRequestHandle

/**
 * Created by Leon on 2022/8/20
 */

inline fun <reified RespType> HttpViewModel.composeBind(task: IFullFutureTask<RespType>): IFullFutureTask<RespType> {
    return task.bindDialogHandle(object : IDialogHandle {
        override fun dialogDismissTiming() {
            this@composeBind.dialogDismissTiming()
        }
        override fun dialogShowTiming(dialogTips: String) {
            this@composeBind.dialogShowTiming(dialogTips)
        }
    }).bindRequestHandle(object : IRequestHandle {
        override fun onRequestHandle(ctx: IRequestContext) {
            this@composeBind.onRequestHandle(object : IRequestContextAdapter {
                override fun cancel() {
                    ctx.cancel()
                }
            })
        }
    })
}