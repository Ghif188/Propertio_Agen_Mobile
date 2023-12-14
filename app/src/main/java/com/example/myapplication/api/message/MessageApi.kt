package com.example.myapplication.api.message

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MessageApi {
    @GET("v1/message")
    fun getMessage(): Call<AllMessagesResponse>

    @GET("v1/message/{messageId}")
    fun getDetailMessage(@Path("messageId") messageId: String) : Call<DetailMessageResponse>
}