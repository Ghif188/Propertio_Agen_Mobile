package com.example.myapplication.api.message

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailMessageResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data: Message?= null

    class Message {
        @SerializedName("id")
        @Expose
        var id: Int? = 0

        @SerializedName("agent_id")
        @Expose
        var agent_id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

        @SerializedName("message")
        @Expose
        var message: String? = null

        @SerializedName("read")
        @Expose
        var read: String? = null

        @SerializedName("subject")
        @Expose
        var subject: String? = null

        @SerializedName("created_at")
        @Expose
        var created_at: String? = null

        @SerializedName("updated_at")
        @Expose
        var updated_at: String? = null
    }
}