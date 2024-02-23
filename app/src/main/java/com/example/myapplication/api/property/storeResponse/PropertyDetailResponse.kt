package com.example.myapplication.api.property.storeResponse

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyDetailResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : PropertySummary? = null

    class PropertySummary {
        @SerializedName("id")
        @Expose
        var id : Int? = 0

        @SerializedName("photo")
        @Expose
        var photo : String? = null

        @SerializedName("headline")
        @Expose
        var headline : String? = null

        @SerializedName("location")
        @Expose
        var location : PropertyLocation? = null

        class PropertyLocation {
            @SerializedName("address")
            @Expose
            var address : String? = null

            @SerializedName("district")
            @Expose
            var district : String? = null

            @SerializedName("city")
            @Expose
            var city : String? = null

            @SerializedName("province")
            @Expose
            var province : String? = null

            @SerializedName("postal_code")
            @Expose
            var postal_code : String? = null

            @SerializedName("latitude")
            @Expose
            var latitude : String? = null

            @SerializedName("longitude")
            @Expose
            var longitude : String? = null
        }

        @SerializedName("price")
        @Expose
        var price : String? = null

        @SerializedName("property_type")
        @Expose
        var property_type : PropertyType? = null

        class PropertyType {
            @SerializedName("id")
            @Expose
            var property_type_id : Int? = 0

            @SerializedName("name")
            @Expose
            var name : String? = null
        }

        @SerializedName("listing_type")
        @Expose
        var listing_type : String? = null

        @SerializedName("certificate")
        @Expose
        var certificate : String? = null

        @SerializedName("posted_at")
        @Expose
        var posted_at : String? = null

        @SerializedName("agent_name")
        @Expose
        var agent_name : String? = null

        @SerializedName("agent_photo")
        @Expose
        var agent_photo : String? = null

        @SerializedName("title")
        @Expose
        var title : String? = null

        @SerializedName("bedroom")
        @Expose
        var bedroom : String? = null

        @SerializedName("bathroom")
        @Expose
        var bathroom : String? = null

        @SerializedName("area")
        @Expose
        var area : String? = null

        @SerializedName("created_at")
        @Expose
        var created_at : String? = null
    }
}