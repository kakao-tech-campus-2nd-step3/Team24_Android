package com.example.challengeonairandroid.model.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Note
    // 앱 전체에서 필요한 모듈들을 정의
}