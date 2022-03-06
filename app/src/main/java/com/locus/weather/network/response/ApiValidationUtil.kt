
package com.locus.weather.network.response

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.locus.weather.R
import com.locus.weather.model.ResponseModel
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection.*
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ApiValidationUtil {
    const val TAG = "ApiValidationUtil"
    fun validateApiException(
        e: Exception,
        context: Context,
        defaultStatusCode: Int = HTTP_INTERNAL_ERROR
    ): ApiServiceException {
        val defaultMessage = context.getString(R.string.something_went_wrong)
        when (e) {
            is HttpException -> return try {
                val msg = getErrorMessage(e.response()!!, context.getString(R.string.something_went_wrong))
                Log.d(TAG, "msg $msg")
                ApiServiceException(msg, e.code())
            } catch (ex: Exception) {
                ApiServiceException(ex.message ?: defaultMessage, e.code())
            }
            is ConnectException, is SocketException -> return ApiServiceException(
                context.getString(R.string.no_network_connection),
                HTTP_INTERNAL_ERROR
            )
            is UnknownHostException -> return ApiServiceException(
                context.getString(R.string.unable_reach_server),
                HTTP_INTERNAL_ERROR
            )
            is SocketTimeoutException -> return ApiServiceException(
                context.getString(R.string.connection_timeout),
                HTTP_INTERNAL_ERROR
            )
            else ->{
                return ApiServiceException(
                    e.message ?: defaultMessage,
                    defaultStatusCode
                )
            }
        }
    }

    private fun <T> getErrorMessage(response: Response<T>, defaultMessage: String): String {
        return try {
            val genericResponse = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
            genericResponse.error.message
        } catch (e: Exception) {
            defaultMessage
        }
    }
}