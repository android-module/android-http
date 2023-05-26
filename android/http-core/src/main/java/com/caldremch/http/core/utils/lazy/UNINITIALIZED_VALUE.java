package com.caldremch.http.core.utils.lazy;

/**
 * Created by Leon on 2022/10/29
 */
public class UNINITIALIZED_VALUE {

    public static UNINITIALIZED_VALUE INSTANCE;

    private UNINITIALIZED_VALUE() {
    }

    static {
        INSTANCE = new UNINITIALIZED_VALUE();
    }
}
