package com.caldremch.http.core.framework

import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.base.IGetRequest
import com.caldremch.http.core.params.Method

/**
 * Created by Leon on 2022/10/30.
 */
class GetRequest(url: String) : BaseRequest<GetRequest>(url, Method.GET),
    IGetRequest<GetRequest> {
    private val request = HttpInitializer.getGetGetExecute()
        private get() = field as IGetExecute

    override suspend fun <T> execute(cls: Class<T>): T? {
        return request.execute(this, transferStation, url, null, cls)
    }
}
