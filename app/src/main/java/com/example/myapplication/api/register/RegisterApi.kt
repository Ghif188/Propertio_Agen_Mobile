package com.example.myapplication.api.register

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegisterApi {
    @Multipart
    @POST("v1/auth/register/user")
    fun registerFoto(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_confirmation: RequestBody,
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("city") city: RequestBody,
        @Part("province") province: RequestBody,
        @Part("role") role: RequestBody,
        @Part("status") status: RequestBody,
        @Part("address") address: RequestBody,
        @Part picture_profile_file: MultipartBody.Part,
    ): Call<RegisterResponse>

    @Multipart
    @POST("v1/auth/register/user")
    fun register(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_confirmation: RequestBody,
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("city") city: RequestBody,
        @Part("province") province: RequestBody,
        @Part("role") role: RequestBody,
        @Part("status") status: RequestBody,
        @Part("address") address: RequestBody
    ): Call<RegisterResponse>
}