package com.example.myapplication.api.admin.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TypePropertyResponse {
    @SerializedName("data")
    @Expose
    var data : List<TypeResponse>? = null

    class TypeResponse {
        @SerializedName("id")
        @Expose
        var id : Int? = null

        @SerializedName("name")
        @Expose
        var name : String? = null

        @SerializedName("icon")
        @Expose
        var icon : String? = null
    }
}