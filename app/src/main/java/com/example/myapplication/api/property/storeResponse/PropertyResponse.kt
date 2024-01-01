package com.example.myapplication.api.property.storeResponse

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PropertyResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : Datas ?= null

    class Datas {
        @SerializedName("properties")
        @Expose
        var properties : List<Property> ?= null

        @SerializedName("pagination")
        @Expose
        var pagination: Pagination? = null
    }

    class Property {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("property_type")
        @Expose
        var property_type: String? = null

        @SerializedName("listing_type")
        @Expose
        var listing_type: String? = null

        @SerializedName("posted_at")
        @Expose
        var posted_at: String? = null

        @SerializedName("price")
        @Expose
        var price: String? = null

        @SerializedName("photo")
        @Expose
        var photo: String? = null

        @SerializedName("address")
        @Expose
        var address: AgentAddress?= null

        class AgentAddress {
            @SerializedName("address")
            @Expose
            var address: String?= null

            @SerializedName("district")
            @Expose
            var district: String?= null

            @SerializedName("city")
            @Expose
            var city: String?= null

            @SerializedName("province")
            @Expose
            var province: String?= null
        }

        @SerializedName("property_code")
        @Expose
        var property_code: String? = null

        @SerializedName("count_views")
        @Expose
        var count_views: Int? = null

        @SerializedName("count_leads")
        @Expose
        var count_leads: Int? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("created_at")
        @Expose
        var created_at: String? = null

        @SerializedName("updated_at")
        @Expose
        var updated_at: String? = null
    }

    class Pagination {
        @SerializedName("current_page")
        @Expose
        var current_page : Int? = 0

        @SerializedName("total")
        @Expose
        var total : Int? = 0

        @SerializedName("per_page")
        @Expose
        var per_page : Int? = 0

        @SerializedName("last_page")
        @Expose
        var last_page : Int? = 0

        @SerializedName("next_page_url")
        @Expose
        var next_page_url : String? = null

        @SerializedName("prev_page_url")
        @Expose
        var prev_page_url : String? = null

        @SerializedName("first_page_url")
        @Expose
        var first_page_url : String? = null

        @SerializedName("last_page_url")
        @Expose
        var last_page_url : String? = null

        @SerializedName("links")
        @Expose
        var links : List<Links>? = null

        class Links {
            @SerializedName("url")
            @Expose
            var url : String? = null

            @SerializedName("label")
            @Expose
            var label : String? = null

            @SerializedName("active")
            @Expose
            var active : String? = null
        }
    }
}