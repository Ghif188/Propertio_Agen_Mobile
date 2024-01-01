package com.example.myapplication.api.property.storeRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangeStatusPropertyRequest {
    @SerializedName("status")
    @Expose
    var status : String? = null
}