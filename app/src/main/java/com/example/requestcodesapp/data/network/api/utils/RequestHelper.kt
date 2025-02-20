package com.example.requestcodesapp.data.network.api.utils

import com.example.requestcodesapp.data.network.api.models.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object RequestHelper {

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ApiResult<T> {
        return try {
            withContext(Dispatchers.IO) {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ApiResult.Success(body)
                    } else {
                        ApiResult.Error("Error empty body")
                    }
                } else {
                    ApiResult.Error("Error: ${response.code()} - ${response.message()}")
                }
            }
        } catch (e: Exception) {
            ApiResult.Error("Exception: ${e.localizedMessage ?: e.message}")
        }
    }

}