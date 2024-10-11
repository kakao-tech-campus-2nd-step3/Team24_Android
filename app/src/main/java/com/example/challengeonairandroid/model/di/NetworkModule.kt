package com.example.challengeonairandroid.model.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.challengeonairandroid.model.api.service.ChallengeApi
import com.example.challengeonairandroid.model.api.service.HistoryApi
import com.example.challengeonairandroid.model.api.service.UserProfileApi
import com.example.challengeonairandroid.model.data.auth.AuthInterceptor
import com.example.challengeonairandroid.model.data.auth.TokenManager
import com.example.challengeonairandroid.model.data.auth.encryptedDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.encryptedDataStore
    }

    @Provides
    @Singleton
    fun provideTokenManager(dataStore: DataStore<Preferences>): TokenManager {
        return TokenManager(dataStore)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.example.com/"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideChallengeApi(retrofit: Retrofit): ChallengeApi {
        return retrofit.create(ChallengeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHistoryApi(retrofit: Retrofit): HistoryApi {
        return retrofit.create(HistoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserProfileApi(retrofit: Retrofit): UserProfileApi {
        return retrofit.create(UserProfileApi::class.java)
    }
}