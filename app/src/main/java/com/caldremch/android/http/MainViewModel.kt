package com.caldremch.android.http

import android.widget.Toast
import com.caldremch.android.http.ext.composeBind
import com.caldremch.android.http.viewmodel.HttpViewModel
import com.caldremch.android.http.viewmodel.ext.IHttpDialogEvent
import com.caldremch.http.core.HttpManager
import com.caldremch.http.core.ext.fullFutureTaskExec
import com.caldremch.http.core.ext.getFullFutureTask
import com.caldremch.http.core.ext.posExec
import com.caldremch.http.core.ext.postFullFutureTask
import com.caldremch.http.core.ext.postFutureTask
import com.caldremch.http.core.framework.base.IFullFutureTask
import com.caldremch.http.core.framework.handle.IDialogHandle
import com.caldremch.http.core.framework.handle.IRequestContext
import com.caldremch.http.core.framework.handle.IRequestHandle

/**
 * Created by Leon on 2022/8/8.
 */

class MainRepository {
    fun getData(): IFullFutureTask<Any> {
        return HttpManager.post("url")
            .postFullFutureTask()
    }

    fun getDataWithChannel(): IFullFutureTask<Any> {
        return HttpManager.post("url")
            .channel(1)
            .postFullFutureTask()
    }


}

class MainViewModel : HttpViewModel() {
    private val repository by lazy { MainRepository() }
    fun getData() {
        composeBind(repository
            .getData())
            .showDialog("showDialog才真正展示dialog")
            .disableToast()
            .fullFutureTaskExec {
            }

        composeBind(repository
            .getDataWithChannel())
            .disableToast()
            .fullFutureTaskExec {
            }
    }
    fun ep(){
//        HttpManager
//            .post("url")
//            .put(Any())
//            .put("key1","value1")
//            .put("key2","value2")
//            .bindDialogHandle(object : IDialogHandle{
//                override fun dialogDismissTiming() {
//                    //关闭dialog
//                }
//
//                override fun dialogShowTiming(dialogTips: String) {
//                    //显示dialog
//                }
//            })
//            .bindRequestHandle(object : IRequestHandle {
//                override fun onRequestHandle(ctx: IRequestContext) {
//                    //可以操作ctx.cancel来取消网络请求
//                }
//            })
//            .disableToast()//关闭toast
//            .formUrlEncoded()//FormUrlEncoded
//            .postQuery()//put的参数将会拼接在请求连接后面
//            .noCustomerHeader()//没有任何的header
//            .path("", "")//能实现Retrofit @Path的功能
//            .showDialog("")//bindDialogHandle不会显示dialog, 只是绑定了一个操作, showDialog才会真正显示
//            .channel(1)// 这里跟IServerUrlConfig对应, channel的值对应的是 不同服务器域名的请求, 默认不调用channel是null为默认服务器
//            .postFutureTask<Any>() //未来执行的任务, 返回的是IFutureTask对象, 根据该对象调用执行方法才会执行, 对应的还有getFutureTask
//            .postFullFutureTask<Any>()//未来执行的任务, 返回的是IFullFutureTask对象, 根据该对象调用执行方法才会执行, 对应的还有getFullFutureTask
//            .posExec<Any>(error = {
//                //error回调
//                ToastUtils.show(it?.message)
//            }) {
//                //success回调
//                ToastUtils.show(Gson().toJson(it))
//            }
//            .posExec<Any> {
//                //只回调success
//            }


    }
}