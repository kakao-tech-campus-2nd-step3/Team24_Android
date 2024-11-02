package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.response.ChallengeReservationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.api.response.HistoryListResponse
import com.example.challengeonairandroid.model.api.response.UserProfileResponse
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateRequest
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateResponse
import com.example.challengeonairandroid.model.api.service.ChallengeApi
import com.example.challengeonairandroid.model.api.service.HistoryApi
import com.example.challengeonairandroid.model.api.service.UserProfileApi
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Singleton
class MyPageRepository @Inject constructor(
    private val userProfileApi: UserProfileApi,
    private val historyApi: HistoryApi,
    private val challengeApi: ChallengeApi
) {
    // 사용자 프로필 조회
    suspend fun getUserProfile(accessToken: String): UserProfileResponse? = withContext(Dispatchers.IO) {
        try {
            val response = userProfileApi.getUserProfile(accessToken)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // 사용자 프로필 수정
    suspend fun updateUserProfile(
        userProfileUpdateRequest: UserProfileUpdateRequest,
        accessToken: String
    ): UserProfileUpdateResponse? = withContext(Dispatchers.IO) {
        try {
            val response = userProfileApi.updateUserProfile(accessToken, userProfileUpdateRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // 회원 챌린지 History 전체 조회
    suspend fun getAllHistory(accessToken: String): HistoryListResponse? = withContext(Dispatchers.IO) {
        try {
            val response = historyApi.getAllHistory(accessToken)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // 챌린지 단건 조회
    suspend fun getChallengeDetails(challengeId: Long): ChallengeResponse? = withContext(Dispatchers.IO) {
        try {
            val date = getCurrentDateTime()
            val response = challengeApi.getChallengeDetails("", challengeId, date)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // 챌린지 예약
    suspend fun reserveChallenge(challengeId: Long): ChallengeReservationResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.reserveChallenge("", challengeId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getCurrentDateTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm"))
    }
}