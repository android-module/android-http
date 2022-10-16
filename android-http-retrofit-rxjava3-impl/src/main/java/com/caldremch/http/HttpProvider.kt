package com.caldremch.http

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.caldremch.http.core.HttpManagerInitializer
import com.caldremch.http.register.HttpInitImpl

/**
 * Created by Leon on 2022/10/16.
 */
class HttpProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        HttpManagerInitializer.register(HttpInitImpl::class.java)
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