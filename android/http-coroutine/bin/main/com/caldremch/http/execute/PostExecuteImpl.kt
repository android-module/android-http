package com.caldremch.http.execute

import com.caldremch.android.log.debugLog
import com.caldremch.http.CoroutineHandler
import com.caldremch.http.core.abs.AbsCallback
import com.caldremch.http.core.framework.base.IFutureTask
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.core.framework.PostRequest
import com.caldremch.http.core.framework.TransferStation
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestHandle
import com.caldremch.http.core.model.ResponseBodyWrapper
import com.caldremch.http.core.params.HttpParams
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

internal class PostExecuteImpl : BaseExecute(), IPostExecute {

    private val gson by lazy { Gson() }


    private inline fun<T> exception(handler:CoroutineHandler<T>, dsl:()->Unit){
        try {
            dsl.invoke()
        } catch (e: CancellationException) {
            debugLog { "ignore CancellationException" }
        } catch (e: Exception) {
            handler.onError(e)
        }
    }

    override fun <T> execute(
        request: PostRequest,
        transferStation: TransferStation,
        url: String,
        callback: AbsCallback<T>,
        clazz: Class<T>
    ) {

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

        if (body != null) {
            val requestBody: RequestBody
            if (body is RequestBody) {
                requestBody = body
            } else {
                requestBody = gson.toJson(body).toRequestBody(MEDIA_TYPE_JSON)
            }
            val job = GlobalScope.launch {
                exception(handler){
                    val resp = api.post(pathUrl, requestBody)
                    handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
                }
            }
            handler.onSubscribe(job)
            return
        }

        //post 空 body
        if (httpParams.isEmpty) {

            val job = GlobalScope.launch {
                exception(handler){
                    val resp = api.post(pathUrl, getHttpParamsBody(httpParams))
                    handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
                }

            }
            handler.onSubscribe(job)
            return
        }

        //post formUrlEncoded
        if (formUrlEncoded) {


            val job = GlobalScope.launch {
                exception(handler){
                    val resp = api.post(pathUrl, httpParams.urlParams)
                    handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
                }
            }
            handler.onSubscribe(job)
            return
        }

        //post动态链接 url后面拼接 key/value
        if (postQuery) {

            val job = GlobalScope.launch {
               exception(handler){
                   val resp = api.postQuery(pathUrl, httpParams.urlParams)
                   handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
               }
            }
            handler.onSubscribe(job)
            return
        }

        //post json body

        val job = GlobalScope.launch {
            exception(handler){
                val resp = api.post(pathUrl, getHttpParamsBody(transferStation.httpParams))
                handler.onNext(convert.convert(ResponseBodyWrapper(resp), clazz))
            }
        }
        handler.onSubscribe(job)
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


    override fun <T> asFullFutureTask(
        request: PostRequest,
        transferStation: TransferStation,
        url: String,
        clazz: Class<T>
    ): IFullFutureTask<T> {
        return object : IFullFutureTask<T> {
            override fun bindDialogHandle(dialogEventHandle: IDialogHandle): IFullFutureTask<T> {
                transferStation.dialogHandle = dialogEventHandle
                return this
            }

            override fun bindRequestHandle(requestHandleEvent: IRequestHandle): IFullFutureTask<T> {
                transferStation.requestHandle = requestHandleEvent
                return this
            }

            override fun disableToast(): IFullFutureTask<T> {
                transferStation.isShowToast = false
                return this
            }

            override fun showDialog(): IFullFutureTask<T> {
                transferStation.showDialog = true
                return this
            }

            override fun showDialog(message: String): IFullFutureTask<T> {
                transferStation.showDialog = true
                transferStation.dialogTips = message
                return this
            }

            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request, transferStation, url, futureCallback, clazz)
            }
        }
    }

    override fun <T> asFutureTask(
        request: PostRequest, transferStation: TransferStation, url: String, clazz: Class<T>
    ): IFutureTask<T> {
        return object : IFutureTask<T> {
            override fun execute(futureCallback: AbsCallback<T>) {
                execute(request, transferStation, url, futureCallback, clazz)
            }
        }
    }

}