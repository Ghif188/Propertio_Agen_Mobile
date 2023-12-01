package com.example.myapplication.api.admin.location

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProvinceResponse : ArrayList<ProvinceResponse.Province>() {
    data class Province(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null
    )
}