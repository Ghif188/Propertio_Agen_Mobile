package com.example.myapplication.api.admin

import com.example.myapplication.api.admin.response.FacilityResponse
import com.example.myapplication.api.admin.response.InfrastructureType
import com.example.myapplication.api.admin.response.TypePropertyResponse
import retrofit2.Call
import retrofit2.http.GET

interface TypeProperty {
    @GET("v1/cms/property-type")
    fun getPropertyType(): Call<TypePropertyResponse>

    @GET("v1/cms/facility-type")
    fun getFacilityType(): Call<FacilityResponse>

    @GET("v1/cms/infrastructure-type")
    fun getInfrastructureType(): Call<InfrastructureType>
}