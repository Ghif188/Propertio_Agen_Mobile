package com.example.myapplication.api.admin.location

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegenciesResponse : ArrayList<RegenciesResponse.Regency>() {
    data class Regency(
        @SerializedName("id")
        @Expose
        var id : String? = null,

        @SerializedName("province_id")
        @Expose
        var province_id : String? = null,

        @SerializedName("name")
        @Expose
        var name : String? = null
    )
}