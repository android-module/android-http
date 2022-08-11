package com.caldremch.android.http

import android.content.Context
import com.caldremch.android.http.viewmodel.ext.IHttpDialogEvent
import com.lxj.xpopup.XPopup

/**
 * Created by Leon on 2022/7/24.
 */
class HttpDialogEventImpl(private val context: Context) : IHttpDialogEvent {

    private val dialog by lazy { XPopup.Builder(context).dismissOnBackPressed(false).dismissOnTouchOutside(false).asLoading() }

    override fun dialogShowTiming() {
        if (dialog.isShow.not()) {
            dialog.show()
        }
    }

    override fun dialogDismissTiming() {
        if (dialog.isShow) {
            dialog.dismiss()
        }
    }
}