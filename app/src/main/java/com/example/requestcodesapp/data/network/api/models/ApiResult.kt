package com.example.requestcodesapp.data.network.api.models

/**
 * Запечатанный класс для обработки ответа с бэка
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}