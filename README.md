![main](https://github.com/android-module/android-http/actions/workflows/android.yml/badge.svg?branch=main)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat-square)](https://android-arsenal.com/api?level=21)


**[http-core最新版本]-->**![maven](https://img.shields.io/maven-central/v/io.github.caldremch/http-core?style=flat-square)

**[android-http-retrofit-rxjava3最新版本]-->**![core](https://img.shields.io/maven-central/v/io.github.caldremch/android-http-retrofit-rxjava3?style=flat-square)

**[coroutine-http最新版本]-->**![core](https://img.shields.io/maven-central/v/io.github.caldremch/coroutine-http?style=flat-square)


## 轻量的 框架-实现 网络请求库, 支持常规的[链式调用]和[泛型]处理

通过对网络请求底层框架的定义,简单的链式调用封装, 关于网络请求库的扩展实现都是基于koin去完成.

## 使用

### 1. 在build.gradle中添加依赖

为了避免耦合, 请使用: runtimeOnly来依赖实现模块

```gradle

//基础库 必须
implementation 'io.github.caldremch:http-core:1.2.2'

//实现库 (retrofit2+rxjava3)
runtimeOnly 'io.github.caldremch:android-http-retrofit-rxjava3:1.0.1'

//实现库 (kotlin协程实现)
runtimeOnly 'io.github.caldremch:coroutine-http:1.0.1'

//自己的实现库
runtimeOnly project(':my-http-impl')
```

实现库可以自己写一个, 或者使用已经扩展好

### 2.初始化

1. 需要先根据的业务情况去定义返回体的解析, 头部数据设置, 实现IHttpInit来注册koin模块, 实现可以参考demo中做法

```kotlin
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
            singleOf<IServerUrlConfig> { HttpUrlConfigImpl() } //多服务器url配置&切换
        }
    }
}
```

2. 在调用网络请求之前(建议在Application的onCreate中)初始化

```kotlin
HttpManagerInitializer.init(MyHttpInit())
```


3. 调用

```kotlin
 HttpManager
            .post("url")
            .put(Any())
            .put("key1","value1")
            .put("key2","value2")
            .bindDialogHandle(object : IDialogHandle{
                override fun dialogDismissTiming() {
                    //关闭dialog
                }

                override fun dialogShowTiming(dialogTips: String) {
                    //显示dialog
                }
            })
            .bindRequestHandle(object : IRequestHandle {
                override fun onRequestHandle(ctx: IRequestContext) {
                    //可以操作ctx.cancel来取消网络请求
                }
            })
            .disableToast()//关闭toast
            .formUrlEncoded()//FormUrlEncoded
            .postQuery()//put的参数将会拼接在请求连接后面
            .noCustomerHeader()//没有任何的header
            .path("", "")//能实现Retrofit @Path的功能
            .showDialog("")//bindDialogHandle不会显示dialog, 只是绑定了一个操作, showDialog才会真正显示
            .channel(1)// 这里跟IServerUrlConfig对应, channel的值对应的是 不同服务器域名的请求, 默认不调用channel是null为默认服务器
            .postFutureTask<Any>() //未来执行的任务, 返回的是IFutureTask对象, 根据该对象调用执行方法才会执行, 对应的还有getFutureTask
            .postFullFutureTask<Any>()//未来执行的任务, 返回的是IFullFutureTask对象, 根据该对象调用执行方法才会执行, 对应的还有getFullFutureTask
            .posExec<Any>(error = {
                //error回调
                ToastUtils.show(it?.message)
            }) { 
                //success回调
                ToastUtils.show(Gson().toJson(it))
            }
            .posExec<Any> { 
                //只回调success
            }

```

其中包含的一些扩展方法, 更多的扩展方法可以查看 http-core里面的 HttpExt.kt文件


# 注意

需要添加gson和koin的依赖








