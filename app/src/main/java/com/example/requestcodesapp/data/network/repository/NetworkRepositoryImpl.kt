package com.example.requestcodesapp.data.network.repository

import com.example.requestcodesapp.data.network.api.interfaces.ApiService
import com.example.requestcodesapp.data.network.api.models.ApiResult
import com.example.requestcodesapp.data.network.api.utils.RequestHelper
import com.example.requestcodesapp.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkRepository {

    /**
     * Получить рандомные числа
     */
    override suspend fun getRandomNumbers(min: Int, max: Int, count: Int): ApiResult<List<Int>> {
        return RequestHelper.safeApiCall {
            apiService.getRandomNumbers(
                min = min,
                max = max,
                count = count
            )
        }
    }
}