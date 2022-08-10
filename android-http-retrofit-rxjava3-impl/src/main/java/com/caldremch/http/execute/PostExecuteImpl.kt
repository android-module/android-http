package com.caldremch.http.execute

import com.caldremch.http.core.AbsCallback
import com.caldremch.http.core.IFutureTask
import com.caldremch.http.core.PostRequest
import com.caldremch.http.core.TransferStation
import com.caldremch.http.core.observer.HttpObservable
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class PostExecuteImpl: BaseExecute(), com.caldremch.http.core.IPostExecute {
    
    private val gson by lazy { Gson() }

    override fun <T> execute(
        request: PostRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ) {

        val formUrlEncoded = transferStation.formUrlEncoded
        val postQuery = transferStation.postQuery
        val httpParams = transferStation.httpParams
        val httpPath = transferStation.httpPath
        val body: Any? = transferStation.requestBody

        val pathUrl =  if(httpPath.isEmpty) url else httpPath.getPathUrl(url)


        if (body != null) {
            val requestBody: RequestBody
            if (body is RequestBody) {
                requestBody = body
            } else {
                requestBody = gson.toJson(body).toRequestBody(MEDIA_TYPE_JSON)
            }
            go<T>(api.post(pathUrl, requestBody), callback, clazz, httpObservable)
            return
        }

        //post 空 body
        if (httpParams.isEmpty) {
            go<T>(api.post(pathUrl, getHttpParamsBody(httpParams)), callback, clazz, httpObservable)
            return
        }

        //post formUrlEncoded
        if (formUrlEncoded) {
            go<T>(api.post(pathUrl, httpParams.urlParams), callback, clazz, httpObservable)
            return
        }

        //post动态链接 url后面拼接 key/value
        if (postQuery) {
            go<T>(api.postQuery(pathUrl, httpParams.urlParams), callback, clazz, httpObservable)
            return
        }

        //post json body
        go<T>(
            api.post(pathUrl, getHttpParamsBody(transferStation.httpParams)),
            callback,
            clazz,
            httpObservable
        )
    }



    fun getHttpParamsBody(httpParams: com.caldremch.http.core.HttpParams): RequestBody {
        if (httpParams.isEmpty) {
            return "{}".toRequestBody(MEDIA_TYPE_JSON)
        }
        return toJsonString(httpParams).toRequestBody(MEDIA_TYPE_JSON)
    }


    companion object {
        private val gson = Gson()
        val MEDIA_TYPE_PLAIN: MediaType = "text/plain;charset=utf-8".toMediaType()
        val MEDIA_TYPE_JSON: MediaType = "application/json;charset=utf-8".toMediaType()
        val MEDIA_TYPE_STREAM: MediaType = "application/octet-stream".toMediaType()
    }


    fun toJsonString(httpParams: com.caldremch.http.core.HttpParams): String {
        return if (!httpParams.isEmpty) {
            gson.toJson(httpParams.getUrlParamsMap())
        } else "{}"
    }

    override fun <T> asFutureTask(
        request: PostRequest, transferStation: TransferStation, url: String, clazz: Class<T>
    ): IFutureTask<T> {
       return asCancelableFutureTask(request,transferStation, url, clazz, null)
    }

    override fun <T> asCancelableFutureTask(
        request: PostRequest,
        transferStation: TransferStation,
        url: String,
        clazz: Class<T>,
        httpObservable: HttpObservable?
    ): IFutureTask<T> {
        return object : IFutureTask<T>{
            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request,transferStation, url, futureCallback, clazz, httpObservable)
            }
        }
    }

}