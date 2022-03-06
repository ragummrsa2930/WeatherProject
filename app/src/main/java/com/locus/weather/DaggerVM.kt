package com.locus.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locus.weather.dager.repository.DaggerDataRepository
import com.locus.weather.model.ResponseModel
import com.locus.weather.network.response.ApiValidationUtil
import com.locus.weather.network.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaggerVM @Inject constructor(val dataRepository: DaggerDataRepository,
                                   @ApplicationContext private val context: Context) : ViewModel() {

    fun getWeatherInfo(cityName: String, callback: (Resource<ResponseModel>) -> Unit) {
        callback(Resource.loading())
        viewModelScope.launch {
            try {
                val result = dataRepository.getWeatherInfo(cityName)
                callback(Resource.success(result))
            }catch (e: Exception){
                callback(Resource.error(ApiValidationUtil.validateApiException(e, context)))
            }
        }
    }

    companion object {
        const val TAG = "DaggerVM"
    }
}