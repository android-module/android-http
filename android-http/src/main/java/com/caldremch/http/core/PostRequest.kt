package com.caldremch.http.core

import com.caldremch.http.core.observer.HttpObservable
import org.koin.java.KoinJavaComponent


/**
 * Created by Leon on 2022/7/8
 */
 class PostRequest(url: String) : BaseRequest<PostRequest>(url, Method.POST),
    IPostRequest<PostRequest> {
    private val request: IPostExecute by KoinJavaComponent.inject(IPostExecute::class.java)

    /**
     * FormUrlEncoded 形式
     */
    var formUrlEncoded = false

    /**
     *  post链接 拼接参数
     */
    var postQuery = false


    var requestBody:Any? = null


    override fun put(body: Any): PostRequest {
        if (type == Method.GET && body is Map<*, *>) {
            httpParams.setUrlParamsMap(body as MutableMap<String, Any>)
            return this
        }

        this.requestBody = body
        return this
    }


    override fun formUrlEncoded(): PostRequest {
        this.formUrlEncoded = true
        return this
    }

    override fun postQuery(): PostRequest {
        this.postQuery = true
        return this
    }


    override fun <T> execute(clazz: Class<T>, callback: AbsCallback<T>) {
        request.execute(this, url, callback, clazz, null)
    }


    override fun <T> asFutureTask(clazz: Class<T>): IFutureTask<T> {
        return request.asFutureTask(this, url, clazz)
    }

    override fun <T> asCancelableFutureTask(
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ): IFutureTask<T> {
        return request.asCancelableFutureTask(this, url, clazz, httpObservable)
    }

}