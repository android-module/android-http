package com.caldremch.android.http

import android.content.Context
import com.caldremch.android.http.adapter.IHttpDialogEvent
import com.caldremch.android.http.demo.HttpCommonHeaderImpl
import com.caldremch.android.http.demo.HttpObsHandlerImpl
import com.caldremch.android.http.demo.HttpUrlConfigImpl
import com.caldremch.http.core.IHttpInit
import com.caldremch.http.core.abs.ICommonRequestEventCallback
import com.caldremch.http.core.abs.IConvertStrategy
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IServerUrlConfig
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Leon on 2022/10/16.
 */
class MyHttpInit : IHttpInit {
    override fun onLoaderCreate(): Module {
        return module {
            singleOf<IHeader> { HttpCommonHeaderImpl() } //通用头部实现
            factoryOf<ICommonRequestEventCallback> { //网络请求声明周期监听
                HttpObsHandlerImpl()
            }
            factoryOf<IConvertStrategy> { //根据业务实现的ResponseBody转换策略
                ConvertStrategyImpl()
            }
            factory<IHttpDialogEvent> { (context: Context) -> HttpDialogEventImpl(context) } //根据业务实现的网络请求弹窗
            singleOf<IServerUrlConfig> { HttpUrlConfigImpl() } //多服务url配置&切换
        }
    }
}