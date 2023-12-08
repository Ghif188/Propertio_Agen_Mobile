package com.example.myapplication.api.property

import com.example.myapplication.api.models.DefaultResponse
import com.example.myapplication.api.property.storeRequest.StorePropertyDetailRequest
import com.example.myapplication.api.property.storeRequest.StorePropertyLocationRequest
import com.example.myapplication.api.property.storeResponse.DeletePropertyResponse
import com.example.myapplication.api.property.storeResponse.PropertyDetailResponse
import com.example.myapplication.api.property.storeResponse.PropertyResponse
import com.example.myapplication.api.property.storeResponse.RepostPropertyResponse
import com.example.myapplication.api.property.storeResponse.StorePropertyDetailResponse
import com.example.myapplication.api.property.storeResponse.StorePropertyLocationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PropertyApi {
    @GET("v1/cms/property-management")
    fun getPropertyList() : Call<PropertyResponse>

    @GET("v1/cms/property-management/property-summary/{propertyId}")
    fun getSummaryProperty(@Path("propertyId") propertyId: String) : Call<PropertyDetailResponse>

    @DELETE("v1/cms/property-management/{propertyId}")
    fun deleteProperty(@Path("propertyId") propertyId: String) : Call<DeletePropertyResponse>

    @POST("v1/cms/property-management/property-location")
    fun storePropertyLocation(
        @Body propertyLocation: StorePropertyLocationRequest
    ) : Call<StorePropertyLocationResponse>

    @POST("v1/cms/property-management/property-detail")
    fun storePropertyDetail(
        @Body propertyDetail: StorePropertyDetailRequest
    ) : Call<StorePropertyDetailResponse>

    @POST("v1/cms/property-management/property-repost/{propertyId}")
    fun repostProperty(@Path("propertyId") propertyId: String) : Call<RepostPropertyResponse>
}