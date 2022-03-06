package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDayHrDataModel(
    @Expose
    @SerializedName("temp_f")
    val temp_f: Double,
    @Expose
    @SerializedName("condition")
    val detailsModel: ForecastDayHrDetailsModel?,
    @Expose
    @SerializedName("feelslike_f")
    val feelslike_f: Double,
    @Expose
    @SerializedName("humidity")
    val humidity: Int

)
