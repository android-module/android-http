package com.caldremch.http.core.execption

/**
 * @author Caldremch
 * @date 2019-08-28 14:39
 * @email caldremch@163.com
 * @describe
 */
class NullDataSuccessException : ApiHttpException(
    ApiCode.SUCC_AND_DATA_NULL,
    "The return is successful, but the data node is null, it is still processed as successful, please ignore this exception message by your situation"
)