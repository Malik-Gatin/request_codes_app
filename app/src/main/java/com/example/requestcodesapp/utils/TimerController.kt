package com.example.requestcodesapp.utils

import kotlinx.coroutines.CoroutineScope

class TimerController(private val scope: CoroutineScope) {

    /**
     * Запускает таймер с заданной длительностью (в секундах)
     * Если таймер уже запущен, предыдущая задача отменяется
     */
    fun startTimer(duration: Int, onFinish: () -> Unit) {

    }

    /**
     * Останавливает текущий таймер
     */
    fun cancelTimer() {

    }
}