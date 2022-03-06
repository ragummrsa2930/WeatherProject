package com.locus.weather.network

import com.locus.weather.model.ResponseModel
import retrofit2.http.*

interface ApiService {

    @GET("v1/forecast.json")
    suspend fun getWeatherInfo(@Query("key") apiKey: String = "cb9d4a287676427ab6e114623220603",
                               @Query("q") cityName: String,
                               @Query("day") day: Int = 4): ResponseModel
}
