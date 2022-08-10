package com.caldremch.http.exception;


/**
 * @author Caldremch
 * @date 2019-08-28 14:39
 * @email caldremch@163.com
 * @describe
 **/
public class NullDataSuccessException extends ApiHttpException {
    public NullDataSuccessException() {
        super(ApiCode.SUCC_AND_DATA_NULL, "返回成功, 但是data节点为 null, 依然按成功处理, 请忽略这条异常信息");
    }
}
