package com.example.requestcodesapp.di.module.network

import com.example.requestcodesapp.data.network.api.interfaces.ApiService
import com.example.requestcodesapp.data.network.repository.NetworkRepositoryImpl
import com.example.requestcodesapp.domain.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(
        apiService: ApiService,
    ): NetworkRepository {
        return NetworkRepositoryImpl(apiService)
    }

}