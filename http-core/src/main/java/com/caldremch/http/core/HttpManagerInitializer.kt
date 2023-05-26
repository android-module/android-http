package com.caldremch.http.core

import com.caldremch.http.core.framework.PostRequest
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

/**
 * Created by Leon on 2022/7/7
 */
object HttpManagerInitializer{

    /**
     * 网络请求实现时,需要注册, 调用改api先注册, 在[init]时, 才会去初始化
     */

    private val iHttpInitClasses = HashSet<Class<out IHttpInit>>()

    fun register(iHttpInitClz: Class<out IHttpInit>){
        iHttpInitClasses.add(iHttpInitClz)
    }

    /**
     * 应用初始化时调用
     * [iHttpInit] 一般为业务所所需要初始化的模块
     */
    fun init(iHttpInit: IHttpInit){
        try {
            KoinJavaComponent.getKoin()
        }catch (e:IllegalStateException){
            startKoin {  }
        }
        val koin =  KoinJavaComponent.getKoin()
        iHttpInitClasses.forEach {
            try {
                KoinJavaComponent.getKoin().loadModules(
                    arrayListOf(it.newInstance().onLoaderCreate())
                )
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        koin.loadModules(arrayListOf(iHttpInit.onLoaderCreate()))
    }
}