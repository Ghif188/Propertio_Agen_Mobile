package com.example.myapplication.api.admin.response

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FacilityResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : List<FacilityResponse>? = null

    class FacilityResponse {
        @SerializedName("id")
        @Expose
        var id : Int? = null

        @SerializedName("name")
        @Expose
        var name : String? = null

        @SerializedName("category")
        @Expose
        var category : String? = null

        @SerializedName("icon")
        @Expose
        var icon : String? = null
    }
}