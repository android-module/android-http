package com.caldremch.http.core

import com.caldremch.http.core.observer.HttpObservable

/**
 *
 * @author Caldremch
 *
 * @date 2020-01-10 09:41
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/

interface IFutureTask<T>{
    fun execute(futureCallback: AbsCallback<T>)
}

interface AnyFutureTask : IFutureTask<Any>

interface ICancel{
    fun onCancel()
}

interface IRequest<out R : IRequest<R>> {
    fun put(key: String, value: Any?): R
    fun path(pathName: String, value: String): R
    fun disableToast(): R
    fun noCustomerHeader(): R
    fun <T> execute( clazz: Class<T>,callback: AbsCallback<T>)
    fun <T> asFutureTask( clazz: Class<T>):IFutureTask<T>
    fun <T> asCancelableFutureTask(clazz: Class<T>,  httpObservable: HttpObservable?):IFutureTask<T>
}


interface ICommonExecute<R>{
    fun <T> asFutureTask(request: R, url:String, clazz: Class<T>): IFutureTask<T>
    fun <T> asCancelableFutureTask(request: R, url:String, clazz: Class<T>, httpObservable:HttpObservable?): IFutureTask<T>
}

interface IGetExecute : ICommonExecute<GetRequest> {
    fun <T> execute(request: GetRequest, url:String, callback: AbsCallback<T>, clazz: Class<T>, httpObservable:HttpObservable?)
}

interface IPureGetExecute : IGetExecute

interface IPostExecute : ICommonExecute<PostRequest> {
    fun <T> execute(request: PostRequest, url:String, callback: AbsCallback<T>, clazz: Class<T>, httpObservable:HttpObservable? )
}


