package com.caldremch.android.coroutine.http.demo.biz

/**
 * Created by Leon on 2022/8/30
 */
object UserManager {

    var gid: String? = null
    var token: String? = null

    fun save(data: UserInfoResp) {
        token = data.clinical
        gid = data.corneal
    }
}