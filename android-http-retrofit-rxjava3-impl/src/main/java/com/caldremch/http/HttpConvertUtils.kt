package com.caldremch.http

import android.content.Context
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Leon on 2022/8/4.
 */
internal object HttpConvertUtils {

    fun convertToFile(context: Context, body: ResponseBody?): File {
        val httpFileDir = context.externalCacheDir?.absolutePath + "/httpcache"
        val file = File(httpFileDir + "/${System.currentTimeMillis()}.jpeg")
        val bodyInputStream = body?.byteStream()
        bodyInputStream?.use {
            val dir = File(httpFileDir)
            if (dir.exists().not()) dir.mkdirs()
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.use {
                val buffer = ByteArray(8192)
                var len = bodyInputStream.read(buffer)
                while (len != -1) {
                    fileOutputStream.write(buffer, 0, len)
                    len = bodyInputStream.read(buffer)
                }
                fileOutputStream.flush()
            }
        }

        return file
    }

    fun convertBytesToFile(context: Context, body: ByteArray?): File {
        val httpFileDir = context.externalCacheDir?.absolutePath + "/httpcache"
        val file = File(httpFileDir + "/${System.currentTimeMillis()}.jpeg")
        val bodyInputStream = ByteArrayInputStream(body)
        bodyInputStream.use {
            val dir = File(httpFileDir)
            if (dir.exists().not()) dir.mkdirs()
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.use {
                val buffer = ByteArray(8192)
                var len = bodyInputStream.read(buffer)
                while (len != -1) {
                    fileOutputStream.write(buffer, 0, len)
                    len = bodyInputStream.read(buffer)
                }
                fileOutputStream.flush()
            }
        }

        return file
    }

}