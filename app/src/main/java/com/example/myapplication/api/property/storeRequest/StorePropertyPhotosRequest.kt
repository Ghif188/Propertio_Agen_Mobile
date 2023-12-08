package com.example.myapplication.api.property.storeRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StorePropertyPhotosRequest {
    @SerializedName("property_id")
    @Expose
    var property_id : String? = null

    @SerializedName("photo_file")
    @Expose
    var photo_file : List<String>? = null
}