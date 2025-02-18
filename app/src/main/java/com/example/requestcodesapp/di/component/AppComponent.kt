package com.example.requestcodesapp.di.component

import com.example.requestcodesapp.di.module.network.NetworkModule
import com.example.requestcodesapp.di.module.network.RetrofitModule
import com.example.requestcodesapp.domain.repository.NetworkRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, NetworkModule::class])
interface AppComponent {

    fun getNetworkRepository(): NetworkRepository

}

object GetterAppComponent {
    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}