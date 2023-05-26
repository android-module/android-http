package com.caldremch.android.http

import android.app.ProgressDialog
import android.content.Context
import com.caldremch.android.http.adapter.IHttpDialogEvent

/**
 * Created by Leon on 2022/7/24.
 */
class HttpDialogEventImpl(private val context: Context) : IHttpDialogEvent {

    private val dialog by lazy { ProgressDialog(context) }

    override fun dialogShowTiming() {
        if (dialog.isShowing.not()) {
            dialog.show()
        }
    }

    override fun dialogDismissTiming() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}