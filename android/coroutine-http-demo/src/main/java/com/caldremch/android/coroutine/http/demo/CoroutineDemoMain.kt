package com.caldremch.android.coroutine.http.demo

import com.caldremch.android.coroutine.http.demo.biz.ApiConstant
import com.caldremch.android.coroutine.http.demo.biz.UserInfoResp
import com.caldremch.android.coroutine.http.demo.impl.ConvertStrategyImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpCommonHeaderImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpObsHandlerImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpUrlConfigImpl
import com.caldremch.android.log.DebugLogInitializer
import com.caldremch.android.log.errorLog
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.HttpManager
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl
import com.caldremch.http.impl.HttpConvertImpl
import kotlinx.coroutines.*

fun initLog() {
    DebugLogInitializer.initLite(true)
}

fun initHttp() {

    //实现端初始化
    HttpInitializer.registerIConvert(HttpConvertImpl::class.java)
    HttpInitializer.registerIGetExecute(GetExecuteImpl::class.java)
    HttpInitializer.registerIPostExecute(PostExecuteImpl::class.java)

    //使用者初始化
    HttpInitializer.Builder()
        .registerHeader(HttpCommonHeaderImpl::class.java)
        .registerRequestEventCallback(HttpObsHandlerImpl::class.java)
        .registerServerUrlConfig(HttpUrlConfigImpl::class.java)
        .registerConvertStrategy(ConvertStrategyImpl::class.java)
        .build()
}

fun startRequest() {
    val scope = CoroutineScope(SupervisorJob())
//    val job = scope.launch {
//        val resp = HttpManager.post(ApiConstant.goApi_getTest)
//            .channel(3)
//            .catchError {
//                errorLog { "my error callback: ${it?.message}  $it" }
//            }
//            .execute(UserInfoResp::class.java)
//        errorLog { "请求结果:$resp" }
//    }

    val loginJob = scope.launch {
        val resp = HttpManager.get(ApiConstant.login)
            .channel(3)
            .passiveCancelCallbackHandle()
            .catchError {
                errorLog { "my error callback2: ${it?.message}  $it" }
            }
            .execute(UserInfoResp::class.java)
    }

    GlobalScope.launch {
        delay(3000)
        loginJob.cancelAndJoin()
//        job.cancelAndJoin()
    }
}

fun main(args: Array<String>) {
    initLog()
    initHttp()
    startRequest()
    Thread.sleep(115000)
}




