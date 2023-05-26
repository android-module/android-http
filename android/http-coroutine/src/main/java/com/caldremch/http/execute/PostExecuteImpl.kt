package com.caldremch.http.execute

import com.caldremch.android.log.debugLog
import com.caldremch.http.Api
import com.caldremch.http.CoroutineHandler
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IBaseResp
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.core.model.ResponseBodyWrapper
import com.caldremch.http.core.params.HttpParams
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.lang.reflect.Type

class PostExecuteImpl : BaseExecute(), IPostExecute {

    private val gson by lazy { Gson() }

    private inline fun <T> exception(handler: CoroutineHandler<T>, dsl: () -> Unit) {
        try {
            dsl.invoke()
        } catch (e: CancellationException) {
            debugLog { "ignore CancellationException" }
        } catch (e: Exception) {
            handler.onError(e)
        }
    }

    override suspend fun <T> execute(
        request: PostRequest,
        ts: TransferStation,
        url: String,
        callback: AbsCallback<IBaseResp<T>>?,
        clazz: Class<T>
    ): IBaseResp<T> {
        return executeType(request, ts, url, callback, clazz)
    }

    override suspend fun <T> executeType(
        postRequest: PostRequest,
        ts: TransferStation,
        url: String,
        callback: AbsCallback<IBaseResp<T>>?,
        clazz: Type
    ): IBaseResp<T> {
        val pathUrl = if (ts.httpPath.isEmpty) url else ts.httpPath.getPathUrl(url)
        val api = getApi(ts.noCustomerHeader, ts.channel)
        val handler = go(callback, ts)
        var convertResult: IBaseResp<T>
        try {
            val resp = getResponse(ts, api, pathUrl)
            convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
            handler.onSuccess(convertResult)
        } catch (e: Exception) {
            convertResult = HttpInitializer.getBaseRespFactory().create(null,  e.findCode(),e.message, null,e.findCode()?.toString())
            handleException(e, ts, handler)
        }
        return convertResult
    }

    private suspend fun getResponse(
        ts: TransferStation,
        api: Api,
        pathUrl: String
    ): ResponseBody {
        val resp = if (ts.requestBody != null) {
            val requestBody: RequestBody
            if (ts.requestBody is RequestBody) {
                requestBody = ts.requestBody as RequestBody
            } else {
                requestBody = gson.toJson(ts.requestBody).toRequestBody(MEDIA_TYPE_JSON)
            }
            api.post(pathUrl, requestBody)
        } else if (ts.httpParams.isEmpty) {
            api.post(pathUrl, getHttpParamsBody(ts.httpParams))
        } else if (ts.formUrlEncoded) {
            api.post(pathUrl, ts.httpParams.urlParams)
        } else {
            api.post(pathUrl, getHttpParamsBody(ts.httpParams))
        }
        return resp
    }



    private fun getHttpParamsBody(httpParams: HttpParams): RequestBody {
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


    fun toJsonString(httpParams: HttpParams): String {
        return if (!httpParams.isEmpty) {
            gson.toJson(httpParams.getUrlParamsMap())
        } else "{}"
    }

}
