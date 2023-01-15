package com.caldremch.http

import com.caldremch.android.log.debugLog
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IHostConfig
import com.caldremch.http.core.abs.IServerUrlConfig
import com.caldremch.http.exception.HostConfigErrorException
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-09 14:57
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 *
 * [setCustomHeader] false, 不设置任何头部参数,因为有些服务器接口传递参数会有问题, 当然, 这是服务器的问题, 但是也做了兼容了
 *
 **/

internal class RequestHelper(private val setCustomHeader: Boolean = true, baseUrl: String? = null) {

    private val defualt_timeout = 20L

    private val gson: Gson = Gson()

    private var clientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder().readTimeout(defualt_timeout, TimeUnit.SECONDS)
            .connectTimeout(defualt_timeout, TimeUnit.SECONDS)

    private var retrofit: Retrofit
    private val config: IServerUrlConfig = HttpInitializer.getServerUrlConfig();
    private val commonHeader: IHeader = HttpInitializer.getHeader();

    init {
        if (setCustomHeader) {
            clientBuilder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request();
                    val requestBuilder = original.newBuilder()
                    val headers = commonHeader.getCommonHeader()
                    headers.forEach {
                        requestBuilder.header(it.key, it.value)
                    }
                    return chain.proceed(requestBuilder.build())
                }
            })
        }

        val loggingInterceptor = okhttp3.logging.HttpLoggingInterceptor(object :
            okhttp3.logging.HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                debugLog { message }
            }
        })
        loggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        var finalUrl = baseUrl
        if (baseUrl.isNullOrEmpty()) {
            val channels = config.channels()
            if (channels.isEmpty()) throw HostConfigErrorException()
            val hostConfig: IHostConfig =
                channels[null] ?: (channels.iterator().next() as IHostConfig)
            finalUrl =
                if (hostConfig.enableConfig()) hostConfig.currentUrl() else hostConfig.defaultUrl()
        }
        retrofit = Retrofit.Builder().baseUrl(finalUrl!!)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(clientBuilder.build())
            .build()
    }


    private fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz)
    }

    fun getApi(): Api {
        return create(Api::class.java)
    }

    companion object {

        val INSTANCE: RequestHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestHelper()
        }
    }
}
