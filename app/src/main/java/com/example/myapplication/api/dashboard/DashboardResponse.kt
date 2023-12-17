package com.example.myapplication.api.dashboard

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DashboardResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data: DetailDashboard? = null

    class DetailDashboard {
        @SerializedName("listing_draft_count")
        @Expose
        var listing_draft_count: Int? = 0

        @SerializedName("listing_active_count")
        @Expose
        var listing_active_count: Int? = 0

        @SerializedName("listing_done_count")
        @Expose
        var listing_done_count: Int? = 0

        @SerializedName("view_count")
        @Expose
        var view_count: Int? = 0

        @SerializedName("lead_count")
        @Expose
        var lead_count: Int? = 0

        @SerializedName("message_count")
        @Expose
        var message_count: Int? = 0

        @SerializedName("leads")
        @Expose
        var leads: Frequently? = null

        @SerializedName("views")
        @Expose
        var views: Frequently? = null

        class  Frequently {
            @SerializedName("monthly")
            @Expose
            var monthly: List<Monthly>? = null

            class Monthly {
                @SerializedName("year")
                @Expose
                var year: String? = null

                @SerializedName("month")
                @Expose
                var month: String? = null

                @SerializedName("total")
                @Expose
                var total: String? = null
            }

            @SerializedName("weekly")
            @Expose
            var weekly: List<Weekly>? = null
            class Weekly {
                @SerializedName("date")
                @Expose
                var date: String? = null

                @SerializedName("total")
                @Expose
                var total: String? = null
            }
        }
    }
}