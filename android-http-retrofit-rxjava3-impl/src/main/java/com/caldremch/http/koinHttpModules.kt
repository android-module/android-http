package com.caldremch.http

import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.framework.base.IGetExecute
import com.caldremch.http.core.framework.base.IPostExecute
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

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