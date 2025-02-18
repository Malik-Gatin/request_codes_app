package com.example.requestcodesapp.domain.usecase

import com.example.requestcodesapp.data.network.api.models.ApiResult
import com.example.requestcodesapp.di.component.GetterAppComponent

class GetRandomNumbersUseCase {

    private val networkRepository by lazy { GetterAppComponent.appComponent.getNetworkRepository() }

    suspend operator fun invoke(min: Int, max: Int, count: Int): ApiResult<List<Int>> {
        return networkRepository.getRandomNumbers(min, max, count)
    }
}