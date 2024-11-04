package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.response.AllChallengesResponse
import com.example.challengeonairandroid.model.api.response.ChallengeCategoryResponse
import com.example.challengeonairandroid.model.api.response.ChallengeCreationRequest
import com.example.challengeonairandroid.model.api.response.ChallengeCreationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeDeletionResponse
import com.example.challengeonairandroid.model.api.response.ChallengeReservationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.api.response.HistoryListResponse
import com.example.challengeonairandroid.model.api.response.HistoryResponse
import com.example.challengeonairandroid.model.api.response.LogoutResponse
import com.example.challengeonairandroid.model.api.response.UserDeletionResponse
import com.example.challengeonairandroid.model.api.response.UserProfileResponse
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateRequest
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateResponse
import com.example.challengeonairandroid.model.api.response.UserRegistrationRequest
import com.example.challengeonairandroid.model.api.response.UserRegistrationResponse
import com.example.challengeonairandroid.model.api.service.ChallengeApi
import com.example.challengeonairandroid.model.api.service.HistoryApi
import com.example.challengeonairandroid.model.api.service.UserApi
import com.example.challengeonairandroid.model.api.service.UserProfileApi
import retrofit2.Response

class Repository(
    private val challengeApi: ChallengeApi,
    private val historyApi: HistoryApi,
    private val userApi: UserApi,
    private val userProfileApi: UserProfileApi
) {
    suspend fun getAllChallenges(accessToken: String): Response<AllChallengesResponse> {
        return challengeApi.getAllChallenges("Bearer $accessToken")
    }

    suspend fun getChallengeDetails(accessToken: String, challengeId: Long, date: String): Response<ChallengeResponse> {
        return challengeApi.getChallengeDetails(accessToken, challengeId, date)
    }

    suspend fun getChallengesByCategory(accessToken: String, categoryId: Int, date: String): Response<ChallengeCategoryResponse> {
        return challengeApi.getChallengesByCategory(accessToken, categoryId, date)
    }

    suspend fun createChallenge(accessToken: String, request: ChallengeCreationRequest): Response<ChallengeCreationResponse> {
        return challengeApi.createChallenge(accessToken, request)
    }

    suspend fun deleteChallenge(accessToken: String, challengeId: Long): Response<ChallengeDeletionResponse> {
        return challengeApi.deleteChallenge(accessToken, challengeId)
    }

    suspend fun reserveChallenge(accessToken: String, challengeId: Long): Response<ChallengeReservationResponse> {
        return challengeApi.reserveChallenge(accessToken, challengeId)
    }

    // History 관련
    suspend fun getHistoryById(accessToken: String, historyId: Long): Response<HistoryResponse> {
        return historyApi.getHistoryById(accessToken, historyId)
    }

    suspend fun getAllHistory(accessToken: String): Response<HistoryListResponse> {
        return historyApi.getAllHistory(accessToken)
    }

    // User 관련
    suspend fun registerUser(request: UserRegistrationRequest): Response<UserRegistrationResponse> {
        return userApi.registerUser(request)
    }

    suspend fun logout(accessToken: String): Response<LogoutResponse> {
        return userApi.logout(accessToken)
    }

    suspend fun deleteUser(accessToken: String): Response<UserDeletionResponse> {
        return userApi.deleteUser(accessToken)
    }

    // UserProfile 관련
    suspend fun getUserProfile(accessToken: String): Response<UserProfileResponse> {
        return userProfileApi.getUserProfile(accessToken)
    }

    suspend fun updateUserProfile(
        accessToken: String,
        request: UserProfileUpdateRequest
    ): Response<UserProfileUpdateResponse> {
        return userProfileApi.updateUserProfile(accessToken, request)
    }
}