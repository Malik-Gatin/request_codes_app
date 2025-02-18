package com.example.requestcodesapp.ui.screen.request_codes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.requestcodesapp.data.network.api.models.ApiResult
import com.example.requestcodesapp.domain.usecase.GetRandomNumbersUseCase
import com.example.requestcodesapp.utils.TimerController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RequestCodesViewModel: ViewModel() {

    private val getRandomNumbersUseCase: GetRandomNumbersUseCase = GetRandomNumbersUseCase()
    // утилиты для таймера
    private val timerController = TimerController(viewModelScope)
    val timerProgress: StateFlow<Float> = timerController.progress
    val timeRemain: StateFlow<Int> = timerController.timeRemain
    // состояние ответа
    private val _codesState = MutableStateFlow<ApiResult<List<String>>>(ApiResult.Loading)
    val codesState: StateFlow<ApiResult<List<String>>> = _codesState.asStateFlow()

    init {
        fetchCodes()
    }

    /**
     * Запрос кодов
     */
    private fun fetchCodes() {
        viewModelScope.launch {
            _codesState.value = ApiResult.Loading // Показываем индикатор

            when (val result = getRandomNumbersUseCase(100_000, 999_999, 10)) {
                is ApiResult.Success -> {
                    _codesState.value = ApiResult.Success(result.data.map { formatCode(it) })
                    reFetchingCodes()
                }
                is ApiResult.Error -> {
                    _codesState.value = result
                    reFetchingCodes()
                }
                else -> {}
            }
        }
    }

    private fun reFetchingCodes() {
        timerController.startTimer(30) {
            fetchCodes() // Запрашиваем новые коды по окончании таймера
        }
    }

    private fun formatCode(code: Int): String {
        return String.format("%06d", code).chunked(3).joinToString(" ")
    }

}