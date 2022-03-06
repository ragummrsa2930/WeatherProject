package com.locus.weather.dager.repository

import com.locus.weather.model.ResponseModel
import com.locus.weather.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerDataRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWeatherInfo(cityName: String): ResponseModel = apiService.getWeatherInfo(cityName = cityName)
}