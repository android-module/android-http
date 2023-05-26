package com.caldremch.http.core.framework

import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.framework.base.IBaseResp
import com.caldremch.http.core.framework.base.IPostRequest
import com.caldremch.http.core.params.Method

/**
 * Created by Leon on 2022/10/30.
 */
class PostRequest(url: String) : BaseRequest<PostRequest>(url, Method.POST),
    IPostRequest<PostRequest> {
    private val request = HttpInitializer.getPostExecute()
    override fun put(body: Any): PostRequest {
        if (type == Method.GET && body is Map<*, *>) {
            transferStation.httpParams.urlParamsMap = (body as Map<String?, Any?>)
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

    override suspend fun <T> execute(cls: Class<T>): IBaseResp<T> {
        return request.execute(this, transferStation, url, null, cls)
    }
}
