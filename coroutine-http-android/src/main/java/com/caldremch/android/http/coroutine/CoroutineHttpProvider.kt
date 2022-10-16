package com.caldremch.android.http.coroutine

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.caldremch.http.core.HttpManagerInitializer

/**
 * Created by Leon on 2022/10/16.
 */
class CoroutineHttpProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        HttpManagerInitializer.register(CoroutineHttpInitImpl::class.java)
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