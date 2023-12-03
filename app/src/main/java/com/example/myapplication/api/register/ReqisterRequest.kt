package com.example.myapplication.api.register

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File

class ReqisterRequest {
    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("password_confirmation")
    @Expose
    var password_confirmation: String? = null

    @SerializedName("first_name")
    @Expose
    var first_name: String? = null

    @SerializedName("last_name")
    @Expose
    var last_name: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null


    @SerializedName("city")
    @Expose
    var city: String? = null


    @SerializedName("province")
    @Expose
    var province: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("picture_profile_file")
    @Expose
    var picture_profile_file: File? = null

    @SerializedName("address")
    @Expose
    var address: String? = null
}