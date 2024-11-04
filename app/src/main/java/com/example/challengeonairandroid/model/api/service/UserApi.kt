package com.example.challengeonairandroid.model.api.service

import com.example.challengeonairandroid.model.api.response.LogoutResponse
import com.example.challengeonairandroid.model.api.response.UserDeletionResponse
import com.example.challengeonairandroid.model.api.response.UserRegistrationRequest
import com.example.challengeonairandroid.model.api.response.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    // 1. 회원 가입
    @POST("api/users/register")
    suspend fun registerUser(
        @Body request: UserRegistrationRequest
    ): Response<UserRegistrationResponse>

    // 2. 로그아웃
    @POST("api/users/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String
    ): Response<LogoutResponse>

    // 3. 회원 삭제
    @DELETE("api/users")
    suspend fun deleteUser(
        @Header("Authorization") accessToken: String
    ): Response<UserDeletionResponse>
}