package com.example.myapplication.api.register

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST

interface RegisterApi {
    @Multipart
    @POST("v1/auth/register/user")
    fun register(
        @Body registerRequest: ReqisterRequest
    ): Call<Response>
}