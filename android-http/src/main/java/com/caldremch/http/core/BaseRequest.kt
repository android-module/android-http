package com.caldremch.http.core


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
    IRequest<R>, ILifecycleObserver {
    val httpParams: HttpParams = HttpParams()
    val httpPath: HttpPath = HttpPath()
    protected var isShowToast = true
     var noCustomerHeader = false

    override fun put(key: String, value: Any?): R {
        value?.let {
            httpParams.put(key, value)
        }
        return this as R
    }


    override fun path(pathName: String, value: String): R {
        httpPath.put(pathName, value)
        return this as R
    }

    override fun disableToast(): R {
        this.isShowToast = false
        return this as R
    }

    override fun noCustomerHeader(): R {
        this.noCustomerHeader = true
        return this as R
    }


    override fun onCancel() {
        super.onCancel()
    }

}



