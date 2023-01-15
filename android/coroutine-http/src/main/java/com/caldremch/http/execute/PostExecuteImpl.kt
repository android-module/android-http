package com.caldremch.http.execute

import com.caldremch.android.log.debugLog
import com.caldremch.http.CoroutineHandler
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.model.ResponseBodyWrapper
import com.caldremch.http.core.params.HttpParams
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

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
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>?,
        clazz: Class<T>
    ): T? {

        val formUrlEncoded = transferStation.formUrlEncoded
        val postQuery = transferStation.postQuery
        val httpParams = transferStation.httpParams
        val httpPath = transferStation.httpPath
        val body: Any? = transferStation.requestBody
        val showDialog = transferStation.showDialog
        val dialogTips = transferStation.dialogTips
        val dialogHandle: IDialogHandle? = transferStation.dialogHandle
        val requestHandle: IRequestHandle? = transferStation.requestHandle
        val isShowToast = transferStation.isShowToast

        val pathUrl = if (httpPath.isEmpty) url else httpPath.getPathUrl(url)

        val api = getApi(transferStation.noCustomerHeader, transferStation.channel)

        val handler = go<T>(
            callback,
            clazz,
            dialogHandle,
            showDialog,
            dialogTips,
            requestHandle,
            isShowToast
        )
        var convertResult: T? = null
        try {
            val resp = if (body != null) {
                val requestBody: RequestBody
                if (body is RequestBody) {
                    requestBody = body
                } else {
                    requestBody = gson.toJson(body).toRequestBody(MEDIA_TYPE_JSON)
                }
                api.post(pathUrl, requestBody)
            } else if (httpParams.isEmpty) {
                api.post(pathUrl, getHttpParamsBody(httpParams))
            } else if (formUrlEncoded) {
                api.post(pathUrl, httpParams.urlParams)
            } else {
                api.post(pathUrl, getHttpParamsBody(transferStation.httpParams))
            }
            convertResult = convert.convert(ResponseBodyWrapper(resp), clazz)
            handler.onSuccess(convertResult)
        } catch (e: Exception) {
            transferStation.errorCallback?.onError(e)
            handler.onError(e)
        }
        return convertResult
    }


    fun getHttpParamsBody(httpParams: HttpParams): RequestBody {
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
