package com.example.challengeonairandroid.model.api.service

import com.example.challengeonairandroid.model.api.response.AuthResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/refresh")
    suspend fun refreshToken(
        @Body refreshToken: String
    ): Response<AuthResponse>
}