package com.example.myapplication.api.admin.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InfrastructureType {
    @SerializedName("data")
    @Expose
    var facility : FacilityResponse? = null

    class FacilityResponse {
        @SerializedName("id")
        @Expose
        var id : Int? = null

        @SerializedName("name")
        @Expose
        var name : String? = null

        @SerializedName("description")
        @Expose
        var description : String? = null

        @SerializedName("icon")
        @Expose
        var icon : String? = null
    }
}