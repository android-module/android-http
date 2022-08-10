package com.caldremch.http.exception;

/**
 *
 * @author Caldremch
 * @date 2019/2/26
 * @Email caldremch@163.com
 * @describe
 *
 **/
public class ApiHttpException extends RuntimeException {

    private String mMsg;
    private int mCode;
    public ApiHttpException(int code, String message){
        super(message);
        this.mCode = code;
    }
    public int getCode() {
        return mCode;
    }
}


