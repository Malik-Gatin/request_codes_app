package com.example.requestcodesapp.data.network.api.interfaces

import com.example.requestcodesapp.data.network.api.constants.ApiVersions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * Получить рандомные числа
     */
    @GET("api/{apiVersion}/random")
    suspend fun getRandomNumbers(
        @Path("apiVersion") apiVersion: String = ApiVersions.V1_0, // Параметр для версии
        @Query("min") min: Int,
        @Query("max") max: Int,
        @Query("count") count: Int
    ): Response<List<Int>>
}