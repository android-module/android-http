package com.caldremch.http.core.framework

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.base.IGetRequest
import com.caldremch.http.core.framework.base.IPostRequest
import com.caldremch.http.core.params.Method
import org.koin.java.KoinJavaComponent.inject


/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 16:43
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
open class GetRequest(url: String) : BaseRequest<GetRequest>(url, Method.GET),
    IGetRequest<GetRequest> {

    private val request: IGetExecute by inject(IGetExecute::class.java)

    override fun <T> execute(clazz: Class<T>, callback: AbsCallback<T>) {
        request.execute(this, transferStation, url, callback, clazz)
    }

    override fun <T> asFutureTask(clazz: Class<T>): IFutureTask<T> {
        return request.asFutureTask(this, transferStation, url, clazz)
    }

    override fun <T> asFullFutureTask(clazz: Class<T>): IFullFutureTask<T> {

        return request.asFullFutureTask(this, transferStation, url, clazz)
    }

}


