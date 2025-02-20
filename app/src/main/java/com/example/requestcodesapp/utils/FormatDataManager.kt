package com.example.requestcodesapp.utils


object FormatDataManager {

    /**
     * Приведение кода под формат: 111111 -> 111 111
     */
    fun formatCode(code: Int): String {
        return String.format("%06d", code).chunked(3).joinToString(" ")
    }

}