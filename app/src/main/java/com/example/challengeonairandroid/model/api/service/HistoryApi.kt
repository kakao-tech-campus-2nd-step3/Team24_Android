package com.example.challengeonairandroid.model.api.service

import com.example.challengeonairandroid.model.api.response.HistoryListResponse
import com.example.challengeonairandroid.model.api.response.HistoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HistoryApi {

    // 1. 회원 챌린지 History 단건 조회
    @GET("api/history/{history_id}")
    suspend fun getHistoryById(
        @Header("Authorization") accessToken: String,
        @Path("history_id") historyId: Long
    ): Response<HistoryResponse>

    // 2. 회원 챌린지 History 전체 조회
    @GET("api/history")
    suspend fun getAllHistory(
        @Header("Authorization") accessToken: String
    ): Response<HistoryListResponse>
}
