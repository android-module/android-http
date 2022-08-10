package com.caldremch.http.core.framework

import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.core.framework.base.IPostRequest
import com.caldremch.http.core.params.Method
import org.koin.java.KoinJavaComponent


/**
 * Created by Leon on 2022/7/8
 */
class PostRequest(url: String) : BaseRequest<PostRequest>(url, Method.POST),
    IPostRequest<PostRequest> {

    private val request: IPostExecute by KoinJavaComponent.inject(IPostExecute::class.java)

    override fun put(body: Any): PostRequest {
        if (type == Method.GET && body is Map<*, *>) {
            transferStation.httpParams.setUrlParamsMap(body as MutableMap<String, Any>)
            return this
        }

        transferStation.requestBody = body
        return this
    }


    override fun formUrlEncoded(): PostRequest {
        transferStation.formUrlEncoded = true
        return this
    }

    override fun postQuery(): PostRequest {
        transferStation.postQuery = true
        return this
    }


    override fun <T> execute(clazz: Class<T>, callback: AbsCallback<T>) {
        request.execute(this, transferStation, url, callback, clazz)
    }


    override fun <T> asFutureTask(clazz: Class<T>): IFutureTask<T> {
        return request.asFutureTask(this, transferStation, url, clazz)
    }


}