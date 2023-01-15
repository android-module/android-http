package com.caldremch.http.core.framework;

import com.caldremch.http.core.abs.IErrorCallback;
import com.caldremch.http.core.framework.handle.IDialogHandle;
import com.caldremch.http.core.framework.handle.IRequestHandle;
import com.caldremch.http.core.params.HttpParams;
import com.caldremch.http.core.params.HttpPath;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Leon on 2022/10/30.
 */
public class TransferStation {

    public IErrorCallback errorCallback;
    private boolean formUrlEncoded;
    private boolean postQuery;
    @Nullable
    private Object requestBody;
    private boolean noCustomerHeader;
    @Nullable
    private IDialogHandle dialogHandle;
    @Nullable
    private IRequestHandle requestHandle;
    private boolean showDialog;
    @Nullable
    private Object channel;
    @NotNull
    private final HttpParams httpParams = new HttpParams();
    @NotNull
    private final HttpPath httpPath = new HttpPath();
    private boolean isShowToast = true;
    @NotNull
    private String dialogTips = "loading...";

    public final boolean getFormUrlEncoded() {
        return this.formUrlEncoded;
    }

    public final void setFormUrlEncoded(boolean z) {
        this.formUrlEncoded = z;
    }

    public final boolean getPostQuery() {
        return this.postQuery;
    }

    public final void setPostQuery(boolean z) {
        this.postQuery = z;
    }

    @Nullable
    public final Object getRequestBody() {
        return this.requestBody;
    }

    public final void setRequestBody(@Nullable Object obj) {
        this.requestBody = obj;
    }

    @NotNull
    public final HttpParams getHttpParams() {
        return this.httpParams;
    }

    @NotNull
    public final HttpPath getHttpPath() {
        return this.httpPath;
    }

    public final boolean isShowToast() {
        return this.isShowToast;
    }

    public final void setShowToast(boolean z) {
        this.isShowToast = z;
    }

    public final boolean getNoCustomerHeader() {
        return this.noCustomerHeader;
    }

    public final void setNoCustomerHeader(boolean z) {
        this.noCustomerHeader = z;
    }

    @Nullable
    public final IDialogHandle getDialogHandle() {
        return this.dialogHandle;
    }

    public final void setDialogHandle(@Nullable IDialogHandle iDialogHandle) {
        this.dialogHandle = iDialogHandle;
    }

    @Nullable
    public final IRequestHandle getRequestHandle() {
        return this.requestHandle;
    }

    public final void setRequestHandle(@Nullable IRequestHandle iRequestHandle) {
        this.requestHandle = iRequestHandle;
    }

    public final boolean getShowDialog() {
        return this.showDialog;
    }

    public final void setShowDialog(boolean z) {
        this.showDialog = z;
    }

    @NotNull
    public final String getDialogTips() {
        return this.dialogTips;
    }

    public final void setDialogTips(@NotNull String str) {
        this.dialogTips = str;
    }

    @Nullable
    public final Object getChannel() {
        return this.channel;
    }

    public final void setChannel(@Nullable Object obj) {
        this.channel = obj;
    }
}
