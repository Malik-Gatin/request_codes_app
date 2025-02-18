package com.example.requestcodesapp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerController(
    private val scope: CoroutineScope
) {
    private var job: Job? = null

    // текущий прогресс
    private val _progress = MutableStateFlow(1f)
    val progress: StateFlow<Float> = _progress

    // оставшееся время
    private val _timeRemain = MutableStateFlow(30)
    val timeRemain: StateFlow<Int> = _timeRemain

    /**
     * Запускаем таймер
     */
    fun startTimer(duration: Int, onFinish: () -> Unit) {
        job?.cancel() // Отменяем предыдущий таймер, если был
        job = scope.launch {
            for (time in duration downTo 0) {
                _timeRemain.value = time
                _progress.value = time / duration.toFloat()
                delay(1000L)
            }
            onFinish()
        }
    }

}