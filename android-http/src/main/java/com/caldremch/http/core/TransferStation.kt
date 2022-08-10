package com.caldremch.http.core

/**
 * Created by Leon on 2022/8/10.
 */
class TransferStation {
    /**
     * FormUrlEncoded 形式
     */
    var formUrlEncoded = false
    /**
     *  post链接 拼接参数
     */
    var postQuery = false
    var requestBody:Any? = null
    val httpParams: HttpParams = HttpParams()
    val httpPath: HttpPath = HttpPath()
    var isShowToast = true
    var noCustomerHeader = false
    var dialogHandle: IDialogHandle? = null
    var requestHandle: IRequestHandle? = null
    var showDialog: Boolean = false
    var dialogTips:String = "loading..."
}