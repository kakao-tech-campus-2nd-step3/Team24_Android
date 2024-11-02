package com.example.challengeonairandroid.model.api.service

import com.example.challengeonairandroid.model.api.response.ChallengeCategoryResponse
import com.example.challengeonairandroid.model.api.response.ChallengeCreationRequest
import com.example.challengeonairandroid.model.api.response.ChallengeCreationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeDeletionResponse
import com.example.challengeonairandroid.model.api.response.ChallengeReservationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.api.response.AllChallengesResponse
import retrofit2.Response
import retrofit2.http.*

interface ChallengeApi {
    @GET("api/challenges")
    suspend fun getAllChallenges(
        @Header("Authorization") accessToken: String
    ): Response<AllChallengesResponse> // 이건 백엔드에서 아직 안 만들어졌음.

    @GET("api/challenges/{challengeId}")
    suspend fun getChallengeDetails(
        @Header("Authorization") accessToken: String,
        @Path("challengeId") challengeId: Long,
        @Body date: String
    ): Response<ChallengeResponse>

    @GET("api/challenges/category/{categoryId}")
    suspend fun getChallengesByCategory(
        @Header("Authorization") accessToken: String,
        @Path("categoryId") categoryId: Int,
        @Query("date") date: String
    ): Response<ChallengeCategoryResponse>

    @POST("api/challenges")
    suspend fun createChallenge(
        @Header("Authorization") accessToken: String,
        @Body request: ChallengeCreationRequest
    ): Response<ChallengeCreationResponse>

    @DELETE("api/challenges/{challengeId}")
    suspend fun deleteChallenge(
        @Header("Authorization") accessToken: String,
        @Path("challengeId") challengeId: Long
    ): Response<ChallengeDeletionResponse>

    @POST("api/challenges/reservation/{challengeId}")
    suspend fun reserveChallenge(
        @Header("Authorization") accessToken: String,
        @Path("challengeId") challengeId: Long
    ): Response<ChallengeReservationResponse>
}