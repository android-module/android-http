package com.caldremch.http.core

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


interface ICancel{
    fun onCancel()
}

interface IRequest<out R : IRequest<R>> {
    fun put(key: String, value: Any?): R
    fun path(pathName: String, value: String): R
    fun bindDialogHandle(dialogEventHandle:IDialogHandle): R
    fun bindRequestHandle(requestHandleEvent: IRequestHandle): R
    fun disableToast(): R
    fun showDialog(message:String): R
    fun noCustomerHeader(): R
    fun <T> execute( clazz: Class<T>,callback: AbsCallback<T>)
    fun <T> asFutureTask( clazz: Class<T>):IFutureTask<T>

}


interface ICommonExecute<R>{
    fun <T> asFutureTask(request: R, transferStation: TransferStation, url:String, clazz: Class<T>): IFutureTask<T>
}

interface IGetExecute : ICommonExecute<GetRequest> {
    fun <T> execute(request: GetRequest, transferStation: TransferStation, url:String, callback: AbsCallback<T>, clazz: Class<T>)
}


interface IPostExecute : ICommonExecute<PostRequest> {
    fun <T> execute(request: PostRequest, transferStation: TransferStation,  url:String, callback: AbsCallback<T>, clazz: Class<T>)
}


