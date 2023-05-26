package com.caldremch.http.core.framework.handle;

/**
 * Created by Leon on 2022/10/30.
 */
public interface IDialogHandle {
   default void dialogShowTiming(String dialogTips) {}
    default void dialogDismissTiming(){}
}
