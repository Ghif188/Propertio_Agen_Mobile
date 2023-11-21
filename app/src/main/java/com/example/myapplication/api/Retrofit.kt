package com.example.myapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit (private val token : String?) {
    private val API_HOST = URL.HOST_API

    fun getRetroClientInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val newRequestBuilder = originalRequest.newBuilder()
            if (token != null) {
                newRequestBuilder.header("Authorization", "Bearer $token")
            }

            val newRequest = newRequestBuilder.build()
            chain.proceed(newRequest)
        }.build()
}