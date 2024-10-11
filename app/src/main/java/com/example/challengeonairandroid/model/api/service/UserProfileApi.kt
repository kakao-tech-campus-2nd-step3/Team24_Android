package com.example.challengeonairandroid.model.api.service

import com.example.challengeonairandroid.model.api.response.UserProfileResponse
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateRequest
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserProfileApi {

    // 1. 회원 정보 조회
    @GET("api/userprofile")
    suspend fun getUserProfile(
        @Header("Authorization") accessToken: String
    ): Response<UserProfileResponse>

    // 2. 회원 정보 수정
    @POST("api/userprofile")
    suspend fun updateUserProfile(
        @Header("Authorization") accessToken: String,
        @Body userProfileUpdateRequest: UserProfileUpdateRequest
    ): Response<UserProfileUpdateResponse>
}
