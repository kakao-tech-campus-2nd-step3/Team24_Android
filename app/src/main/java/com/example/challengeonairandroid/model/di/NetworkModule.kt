package com.example.challengeonairandroid.model.di

import com.example.challengeonairandroid.model.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // Note
    // Provides 어노테이션이 붙은 것들에 대해 Inject 어노테이션을 사용하면 개발자가 정의한 방식대로 의존성을 주입
    // Provides 어노테이션이 붙지 않은 것들에 대해 Inject 어노테이션을 사용하면 Hilt가 자동으로 의존성을 주입
    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.example.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}