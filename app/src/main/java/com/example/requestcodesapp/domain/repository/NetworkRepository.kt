package com.example.requestcodesapp.domain.repository

import com.example.requestcodesapp.data.network.api.models.ApiResult

interface NetworkRepository {
    suspend fun getRandomNumbers(min: Int, max: Int, count: Int): ApiResult<List<Int>>
}