package com.caldremch.http

import android.content.Context
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.IGetExecute
import com.caldremch.http.core.IPostExecute
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IObserverHandler
import com.caldremch.http.core.abs.IServerUrlConfig
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl

import okhttp3.ResponseBody
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Leon on 2022/7/8
 */
val koinHttpModules = module {
    scope<HttpManager> {
        //http
        factoryOf<IGetExecute> { GetExecuteImpl() }
        factoryOf<IPostExecute> { PostExecuteImpl() }

    }


}