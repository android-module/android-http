package com.caldremch.http.execute

import com.caldremch.http.core.execption.ApiHttpException

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */

fun Exception.findCode():Int?{
    if(this is ApiHttpException){
        return this.code
    }
    return  null
}
