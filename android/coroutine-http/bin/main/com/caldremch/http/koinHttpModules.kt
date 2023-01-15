package com.caldremch.http

import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.abs.IConvert
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl
import com.caldremch.http.impl.HttpConvertImpl
import okhttp3.ResponseBody

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/**
 * Created by Leon on 2022/7/8
 * 框架内部实现的加载
 */
val koinHttpModules = module {
    scope<HttpManager> {
        factoryOf<IGetExecute> { GetExecuteImpl() }
        factoryOf<IPostExecute> { PostExecuteImpl() }
        factoryOf<IConvert<ResponseBody>> { HttpConvertImpl() }
    }
}