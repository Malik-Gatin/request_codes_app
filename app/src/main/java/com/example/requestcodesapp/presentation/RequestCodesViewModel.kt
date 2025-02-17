package com.example.requestcodesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.requestcodesapp.utils.TimerController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RequestCodesViewModel: ViewModel() {

    private val timerController = TimerController(viewModelScope)

    // состояния таймера
    val timerProgress: StateFlow<Float> = MutableStateFlow(1f)
    val timeRemain: StateFlow<Int> = MutableStateFlow(30)

    fun startTimer(duration: Int, onFinish: () -> Unit) {
        timerController.startTimer(duration, onFinish)
    }

}