package com.example.myapplication.api.property.storeResponse

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepostPropertyResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : List<PropertyData>? = null

    class PropertyData {
        @SerializedName("id")
        @Expose
        var id : Int? = 0

        @SerializedName("user_id")
        @Expose
        var userId: String? = null

        @SerializedName("title")
        @Expose
        var title: String? = null
    }
}