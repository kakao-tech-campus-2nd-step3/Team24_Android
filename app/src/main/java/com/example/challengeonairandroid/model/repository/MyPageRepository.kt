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

@Singleton
class MyPageRepository @Inject constructor(
    private val userProfileApi: UserProfileApi,
    private val historyApi: HistoryApi,
    private val challengeApi: ChallengeApi
) {
    // 사용자 프로필 조회
    suspend fun getUserProfile(accessToken: String): UserProfileResponse? = withContext(Dispatchers.IO) {
        try {
            val response = userProfileApi.getUserProfile("Bearer $accessToken") // Bearer 방식으로 액세스 토큰 추가
            if (response.isSuccessful) {
                response.body() // 성공 시 UserProfileResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // 사용자 프로필 수정
    suspend fun updateUserProfile(userProfileUpdateRequest: UserProfileUpdateRequest, accessToken: String): UserProfileUpdateResponse? = withContext(Dispatchers.IO) {
        try {
            val response = userProfileApi.updateUserProfile("Bearer $accessToken", userProfileUpdateRequest)
            if (response.isSuccessful) {
                response.body() // 성공 시 UserProfileUpdateResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // 회원 챌린지 History 전체 조회
    suspend fun getAllHistory(accessToken: String): HistoryListResponse? = withContext(Dispatchers.IO) {
        try {
            val response = historyApi.getAllHistory("Bearer $accessToken")
            if (response.isSuccessful) {
                response.body() // 성공 시 HistoryListResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // 챌린지 단건 조회
    suspend fun getChallengeDetails(challengeId: Long): ChallengeResponse? = withContext(Dispatchers.IO) {
        val date = "2024-09-05:07:25"
        try {
            val response = challengeApi.getChallengeDetails(challengeId, date)

            if (response.isSuccessful) {
                response.body()  // 성공 시 ChallengeResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // 챌린지 예약
    suspend fun reserveChallenge(challengeId: Long): ChallengeReservationResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.reserveChallenge(challengeId)

            if (response.isSuccessful) {
                response.body() // 성공 시 ChallengeReservationResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

// 혹시 모르니까 놔두는 회원 챌린지 History 단건 조회
//    suspend fun getHistoryById(accessToken: String, historyId: Long): HistoryResponse? = withContext(Dispatchers.IO) {
//        try {
//            val response = historyApi.getHistoryById("Bearer $accessToken", historyId)
//            if (response.isSuccessful) {
//                response.body() // 성공 시 HistoryResponse 반환
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            null
//        }
//    }
}
