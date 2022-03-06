package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDayHrDetailsModel(
    @Expose
    @SerializedName("text")
    val text: String
)
