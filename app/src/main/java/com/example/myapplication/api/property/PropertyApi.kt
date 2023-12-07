package com.example.myapplication.api.property

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyApi {
    @GET("v1/cms/property-management")
    fun getPropertyList() : Call<PropertyResponse>

    @DELETE("v1/cms/property-management/{propertyId}")
    fun deleteProperty(@Path("propertyId") propertyId: String) : Call<DelPropertyResponse>
}