package com.caldremch.http.core.framework

import com.caldremch.http.core.abs.IErrorCallback
import com.caldremch.http.core.framework.base.IRequest
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.params.Method

/**
 * Created by Leon on 2022/10/30.
 */
abstract class BaseRequest<R : IRequest<R>>(/* JADX INFO: Access modifiers changed from: protected */
                                            protected val url: String, /* JADX INFO: Access modifiers changed from: protected */
                                            protected var type: Method
) : IRequest<R> {

    /* JADX INFO: Access modifiers changed from: protected */
    protected val transferStation by lazy { TransferStation() }

    override fun channel(channel: Any?): R {
        transferStation.setChannel(channel)
        return this as R
    }

    override fun put(key: String, value: Any?): R {
        if (value != null) {
            transferStation.getHttpParams().put(key, value)
        }
        return this as R
    }

    override fun bindDialogHandle(dialogEventHandle: IDialogHandle): R {
        transferStation.setDialogHandle(dialogEventHandle)
        return this as R
    }

    override fun bindRequestHandle(requestHandleEvent: IRequestHandle): R {
        transferStation.setRequestHandle(requestHandleEvent)
        return this as R
    }

    override fun path(pathName: String, value: String): R {
        transferStation.getHttpPath().put(pathName, value)
        return this as R
    }

    override fun disableToast(): R {
        transferStation.setShowToast(false)
        return this as R
    }

    override fun showDialog(): R {
        transferStation.setShowDialog(true)
        transferStation.setDialogTips("")
        return this as R
    }

    override fun showDialog(message: String): R {
        transferStation.setShowDialog(true)
        transferStation.setDialogTips(message)
        return this as R
    }

    override fun noCustomerHeader(): R {
        transferStation.setNoCustomerHeader(true)
        return this as R
    }

    override fun catchError(errorBlock: IErrorCallback): R {
        //单次回调
        transferStation.errorCallback =  errorBlock
        return this as R
    }
}
