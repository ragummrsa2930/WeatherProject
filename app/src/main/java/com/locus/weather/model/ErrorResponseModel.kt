package com.locus.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(
    @Expose
    @SerializedName("code")
    val code: Int,
    @Expose
    @SerializedName("message")
    val message: String

)
