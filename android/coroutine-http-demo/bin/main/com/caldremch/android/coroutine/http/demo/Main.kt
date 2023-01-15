package com.caldremch.android.coroutine.http.demo

import com.caldremch.android.coroutine.http.demo.biz.LoginApi
import com.caldremch.android.coroutine.http.demo.biz.UserInfoResp
import com.caldremch.android.coroutine.http.demo.biz.UserManager
import com.caldremch.android.coroutine.http.demo.impl.ConvertStrategyImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpCommonHeaderImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpObsHandlerImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpUrlConfigImpl
import com.caldremch.android.log.DebugLogInitializer
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.abs.IConvertStrategy
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IServerUrlConfig
import com.caldremch.http.core.ext.posExec
import com.caldremch.http.core.ext.post
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import javax.naming.Context

fun initLog() {
    DebugLogInitializer.init(true)
}

fun initHttp() {
    startKoin {
//        modules(koinHttpModules)
        modules(module {
            singleOf<IHeader> { HttpCommonHeaderImpl() }
            factoryOf<ICommonRequestEventCallback> {
                HttpObsHandlerImpl()
            }
            factoryOf<IConvertStrategy> {
                ConvertStrategyImpl()
            }
            factoryOf<IConvertStrategy> { (context:Context)-> ConvertStrategyImpl() }
            singleOf<IServerUrlConfig> { HttpUrlConfigImpl() }
        })
    }
}

fun startRequest() {

    val phone = "xxxxx"
    val pwd = "1111"
    HttpManager.post(LoginApi.login).put("xxx", phone)
        .put("password", pwd).posExec<UserInfoResp>(error = {
            debugLog { "错误了" }
        }) {
            UserManager.save(it!!)

            post("/xxxx/2").channel(1).put("xxxx", "{}").posExec<Any> {

            }
        }


}

fun main(args: Array<String>) {
    initLog()
    initHttp()
    startRequest()
    Thread.sleep(5000)
}




