package com.caldremch.http.core.execption;

/**
 * Created by Leon on 2022/10/30.
 */
public class NullDataSuccessException extends ApiHttpException{
    public NullDataSuccessException() {
        super(ApiCode.SUCC_AND_DATA_NULL, "The return is successful, but the data node is null, it is still processed as successful, please ignore this exception message by your situation");
    }
}
