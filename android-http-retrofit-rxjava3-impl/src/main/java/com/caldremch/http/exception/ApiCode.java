package com.caldremch.http.exception;

/**
 * @author Caldremch
 * @date 2019-02-26 11:22
 * @describe
 **/
public class ApiCode {
    //根据服务端状态 自定义 code
    public final static int NOT_LOGIN = 0x10001;
    public final static int ERROR = 0x10002;
    public final static int JSON_ERROR = 0x10003;
    public final static int SUCC_AND_DATA_NULL = 0x10004;
}
