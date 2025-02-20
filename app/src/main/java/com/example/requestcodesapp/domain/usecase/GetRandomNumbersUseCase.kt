package com.example.requestcodesapp.domain.usecase

import com.example.requestcodesapp.data.network.api.models.ApiResult
import com.example.requestcodesapp.di.component.GetterAppComponent
import com.example.requestcodesapp.utils.FormatDataManager

class GetRandomNumbersUseCase {

    private val networkRepository by lazy { GetterAppComponent.appComponent.getNetworkRepository() }

    suspend operator fun invoke(min: Int, max: Int, count: Int): ApiResult<List<String>> {
        return networkRepository.getRandomNumbers(min, max, count).toFormattedCodes()
    }

    private fun ApiResult<List<Int>>.toFormattedCodes(): ApiResult<List<String>> = when (this) {
        is ApiResult.Success -> ApiResult.Success(this.data.map { FormatDataManager.formatCode(it) })
        is ApiResult.Error -> this
        is ApiResult.Loading -> this
    }
}