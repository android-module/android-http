package com.caldremch.http.core

import com.caldremch.http.core.observer.HttpObservable
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
        request.execute(this, transferStation, url, callback, clazz, null)
    }


    override fun <T> asFutureTask(clazz: Class<T>): IFutureTask<T> {
        return request.asFutureTask(this, transferStation, url, clazz)
    }

    override fun <T> asCancelableFutureTask(
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ): IFutureTask<T> {
        return request.asCancelableFutureTask(this, transferStation, url, clazz, httpObservable)
    }

}