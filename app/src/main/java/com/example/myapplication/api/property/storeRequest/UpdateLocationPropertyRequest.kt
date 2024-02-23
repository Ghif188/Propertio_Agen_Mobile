package com.example.myapplication.api.property.storeRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateLocationPropertyRequest {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("listing_type")
    @Expose
    var listing_type: String? = null

    @SerializedName("certificate")
    @Expose
    var certificate: String? = null

    @SerializedName("district")
    @Expose
    var district: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("province")
    @Expose
    var province: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null
}