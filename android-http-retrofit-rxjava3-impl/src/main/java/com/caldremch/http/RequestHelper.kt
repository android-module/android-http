package com.caldremch.http

import com.caldremch.android.http.Api
import com.caldremch.android.log.debugLog
import com.caldremch.http.core.abs.IHeader
import com.caldremch.http.core.abs.IServerUrlConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
 **/

internal class RequestHelper(private val setCustomHeader:Boolean = true) {

    private val defualt_timeout = 20L

    private val gson: Gson = Gson()

    private var clientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder().readTimeout(defualt_timeout, TimeUnit.SECONDS)
            .connectTimeout(defualt_timeout, TimeUnit.SECONDS)

    private var retrofit: Retrofit
    private val config: IServerUrlConfig by KoinJavaComponent.inject(IServerUrlConfig::class.java)
    private val commonHeader: IHeader by KoinJavaComponent.inject(IHeader::class.java)

    init {

        if(setCustomHeader){
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

        val baseUrl = if (config.enableConfig()) config.currentUrl() else config.defaultUrl()
        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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