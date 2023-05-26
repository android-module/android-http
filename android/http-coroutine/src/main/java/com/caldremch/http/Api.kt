package com.caldremch.http

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @author Caldremch
 * @date 2019-02-22 17:51
 * @describe
 */
interface Api {

    @GET
    @Headers("Content-Type:image/jpeg; charset=utf-8")
    suspend fun getImage(@Url url: String, @QueryMap maps: MutableMap<String, Any>): ResponseBody

    @GET
    suspend fun get(@Url url: String, @QueryMap maps: MutableMap<String, Any>): ResponseBody

    @GET
    suspend fun get(@Url url: String): ResponseBody

    @POST
    suspend fun post(@Url url: String, @Body requestBody: RequestBody): ResponseBody

    @POST
    suspend fun postQuery(@Url url: String, @QueryMap maps: MutableMap<String, Any>): ResponseBody

    @POST
    suspend fun post(@Url url: String): ResponseBody

    @FormUrlEncoded
    @POST
    suspend fun post(@Url url: String, @FieldMap requestData: MutableMap<String, Any>): ResponseBody

    @Multipart
    @POST("File/upload")
    suspend fun upload(@PartMap requestData: MutableMap<String, Any>): ResponseBody

    @Multipart
    @POST("File/upload")
    suspend fun upload(@Part parts: List<MultipartBody.Part>): ResponseBody

    @Streaming
    @GET
    suspend fun downloadImage(@Url url: String): Call<ResponseBody>
}
