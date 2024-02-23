package com.example.myapplication.api.profile

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailProfileResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data: ProfileSummary? = null

    class ProfileSummary {
        @SerializedName("id")
        @Expose
        var id: Int? = 0

        @SerializedName("account_id")
        @Expose
        var account_id: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("role")
        @Expose
        var role: String? = null

        @SerializedName("user_data")
        @Expose
        var user_data: UserDetail? = null

        class UserDetail {
            @SerializedName("full_name")
            @Expose
            var full_name: String? = null

            @SerializedName("phone")
            @Expose
            var phone: String? = null

            @SerializedName("address")
            @Expose
            var address: String? = null

            @SerializedName("city")
            @Expose
            var city: String? = null

            @SerializedName("province")
            @Expose
            var province: String? = null

            @SerializedName("picture_profile")
            @Expose
            var picture_profile: String? = null
        }

        @SerializedName("user_addition")
        @Expose
        var user_addition: DetailAddition? = null

        class DetailAddition {
            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("website")
            @Expose
            var website: String? = null

            @SerializedName("adcredits")
            @Expose
            var adcredits: String? = null
        }
    }
}