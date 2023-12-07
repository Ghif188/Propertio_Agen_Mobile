package com.example.myapplication.api.property

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DelPropertyResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    val data : Int? = 0
}