package com.caldremch.http.core
import org.koin.core.module.Module
/**
 * Created by Leon on 2022/10/16.
 * Http的初始化器, 用于加载额外的koin模块
 */
interface IHttpInit {
    fun onLoaderCreate():Module
}