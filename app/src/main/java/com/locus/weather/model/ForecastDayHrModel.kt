package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDayHrModel(
    @Expose
    @SerializedName("hour")
    val hourDataList: MutableList<ForecastDayHrDataModel> = mutableListOf()
)
