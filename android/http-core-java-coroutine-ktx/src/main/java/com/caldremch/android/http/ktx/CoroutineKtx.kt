package com.caldremch.android.http.ktx

import com.caldremch.http.core.framework.BaseRequest
import com.caldremch.http.core.framework.GetRequest
import com.caldremch.http.core.framework.base.IRequest

/**
 * @author Leon
 * @date 2023/1/14
 * @desc
 */

suspend  fun < Request: IRequest<Request>>  BaseRequest<Request>.catch(block:(e:Throwable?)->Unit) :Request{

    return this as Request
}
