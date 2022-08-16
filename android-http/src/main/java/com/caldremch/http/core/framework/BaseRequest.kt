package com.caldremch.http.core.framework

import com.caldremch.http.core.framework.base.IRequest
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.params.Method


/**
 *
 * @author Caldremch
 *
 * @date 2020-01-10 09:41
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
abstract class BaseRequest<R : IRequest<R>>(val url: String, @Method internal var type: Int) :
    IRequest<R> {

    protected val transferStation by lazy { TransferStation() }

    override fun put(key: String, value: Any?): R {
        value?.let {
            transferStation.httpParams.put(key, value)
        }
        return this as R
    }

    override fun bindDialogHandle(dialogEventHandle: IDialogHandle): R {
        transferStation.dialogHandle = dialogEventHandle
        return this as R
    }

    override fun bindRequestHandle(requestHandleEvent: IRequestHandle):  R {
        transferStation.requestHandle = requestHandleEvent
        return this as R
    }


    override fun path(pathName: String, value: String): R {
        transferStation.httpPath.put(pathName, value)
        return this as R
    }

    override fun disableToast(): R {
        transferStation.isShowToast = false
        return this as R
    }

    override fun showDialog(): R {
        transferStation.showDialog = true
        transferStation.dialogTips = ""
        return this as R
    }

    override fun showDialog(message: String): R {
        transferStation.showDialog = true
        transferStation.dialogTips = message
        return this as R
    }

    override fun noCustomerHeader(): R {
        transferStation.noCustomerHeader = true
        return this as R
    }

}



