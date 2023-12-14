package com.example.myapplication.api.profile

import retrofit2.Call
import retrofit2.http.GET

interface ProfileApi {
    @GET("v1/profile")
    fun getDetailProfile(): Call<DetailProfileResponse>
}