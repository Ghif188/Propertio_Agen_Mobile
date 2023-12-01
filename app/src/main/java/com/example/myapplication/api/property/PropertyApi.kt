package com.example.myapplication.api.property

import retrofit2.Call
import retrofit2.http.GET

interface PropertyApi {
    @GET("v1/cms/property-management")
    fun getPropertyList() : Call<PropertyResponse>
}