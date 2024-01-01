package com.example.myapplication.api.property.storeResponse

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatusPropertyResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : DetailData ?= null

    class DetailData {
        @SerializedName("id")
        @Expose
        var id : Int? = 0

        @SerializedName("property_type_id")
        @Expose
        var property_type_id : Int? = 0

        @SerializedName("agent_id")
        @Expose
        var agent_id : Int? = 0

        @SerializedName("listing_class")
        @Expose
        var listing_class : String? = null

        @SerializedName("property_code")
        @Expose
        var property_code : String? = null

        @SerializedName("listing_type")
        @Expose
        var listing_type : String? = null

        @SerializedName("title")
        @Expose
        var title : String? = null

        @SerializedName("headline")
        @Expose
        var headline : String? = null

        @SerializedName("province")
        @Expose
        var province : String? = null

        @SerializedName("city")
        @Expose
        var city : String? = null

        @SerializedName("district")
        @Expose
        var district : String? = null

        @SerializedName("address")
        @Expose
        var address : String? = null

        @SerializedName("postal_code")
        @Expose
        var postal_code : String? = null

        @SerializedName("longitude")
        @Expose
        var longitude : String? = null

        @SerializedName("latitude")
        @Expose
        var latitude : String? = null

        @SerializedName("certificate")
        @Expose
        var certificate : String? = null

        @SerializedName("status")
        @Expose
        var status : String? = null

        @SerializedName("slug")
        @Expose
        var slug : String? = null

        @SerializedName("posted_at")
        @Expose
        var posted_at : String? = null

        @SerializedName("created_at")
        @Expose
        var created_at : String? = null

        @SerializedName("updated_at")
        @Expose
        var updated_at : String? = null
    }
}