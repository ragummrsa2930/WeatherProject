package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @Expose
    @SerializedName("forecast")
    val forecast: ForecastDayModel?,
    @Expose
    @SerializedName("error")
    val error: ErrorResponseModel

)
