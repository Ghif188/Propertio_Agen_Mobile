package com.example.myapplication.api.property.storeResponse

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StorePropertyDetailResponse : DefaultResponse() {
    @SerializedName("data")
    @Expose
    var data : PropertyDetail? = null

    class PropertyDetail {
        @SerializedName("property_id")
        @Expose
        var property_id: String? = null

        @SerializedName("prize")
        @Expose
        var prize: String? = null

        @SerializedName("price_type")
        @Expose
        var price_type: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("surface_area")
        @Expose
        var surface_area: String? = null

        @SerializedName("building_area")
        @Expose
        var building_area: String? = null

        @SerializedName("floor")
        @Expose
        var floor: String? = null

        @SerializedName("bedroom")
        @Expose
        var bedroom: String? = null

        @SerializedName("bathroom")
        @Expose
        var bathroom: String? = null

        @SerializedName("garage")
        @Expose
        var garage: String? = null

        @SerializedName("year_built")
        @Expose
        var year_built: String? = null

        @SerializedName("position")
        @Expose
        var position: String? = null

        @SerializedName("power_supply")
        @Expose
        var power_supply: String? = null

        @SerializedName("condition")
        @Expose
        var condition: String? = null

        @SerializedName("water_type")
        @Expose
        var water_type: String? = null

        @SerializedName("interior")
        @Expose
        var interior: String? = null

        @SerializedName("facing")
        @Expose
        var facing: String? = null

        @SerializedName("road_access")
        @Expose
        var road_access: String? = null

        @SerializedName("quality")
        @Expose
        var quality: Int? = 0

        @SerializedName("id")
        @Expose
        var id : Int? = 0
    }
}