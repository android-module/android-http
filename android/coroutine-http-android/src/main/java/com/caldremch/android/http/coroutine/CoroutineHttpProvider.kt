package com.caldremch.android.http.coroutine

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl
import com.caldremch.http.impl.HttpConvertImpl

/**
 * Created by Leon on 2022/10/16.
 */
class CoroutineHttpProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        HttpInitializer.registerIConvert(HttpConvertImpl::class.java)
        HttpInitializer.registerIPostExecute(PostExecuteImpl::class.java)
        HttpInitializer.registerIGetExecute(GetExecuteImpl::class.java)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0

    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}
