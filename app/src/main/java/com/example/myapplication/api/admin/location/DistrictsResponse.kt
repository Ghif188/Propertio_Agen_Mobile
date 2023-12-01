package com.example.myapplication.api.admin.location

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DistrictsResponse : ArrayList<DistrictsResponse.District>() {
    data class District (
        @SerializedName("id")
        @Expose
        var id : String? = null,

        @SerializedName("regency_id")
        @Expose
        var regency_id : String? = null,

        @SerializedName("name")
        @Expose
        var name : String ?= null
    )
}