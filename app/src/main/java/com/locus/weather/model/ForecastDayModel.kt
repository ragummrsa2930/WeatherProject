package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDayModel(
    @Expose
    @SerializedName("forecastday")
    val forcasetDayList: MutableList<ForecastDayHrModel> = mutableListOf()
)
