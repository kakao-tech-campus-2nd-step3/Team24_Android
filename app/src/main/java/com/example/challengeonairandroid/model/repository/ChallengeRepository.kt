package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.service.ChallengeApi
import com.example.challengeonairandroid.model.api.response.ChallengeCategoryResponse
import com.example.challengeonairandroid.model.api.response.ChallengeCreationRequest
import com.example.challengeonairandroid.model.api.response.ChallengeCreationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeDeletionResponse
import com.example.challengeonairandroid.model.api.response.ChallengeReservationResponse
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class ChallengeRepository @Inject constructor(
    private val challengeApi: ChallengeApi
) {
    suspend fun getChallengeDetails(challengeId: Long): ChallengeResponse? = withContext(Dispatchers.IO) {
        val date = getCurrentDateTime()
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

    suspend fun getChallengesByCategory(categoryId: Int): ChallengeCategoryResponse? = withContext(Dispatchers.IO) {
        val date = getCurrentDateTime()
        try {
            val response = challengeApi.getChallengesByCategory(categoryId, date)
            if (response.isSuccessful) {
                response.body() // 성공 시 ChallengeCategoryResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    suspend fun createChallenge(challenge: ChallengeCreationRequest): ChallengeCreationResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.createChallenge(challenge)

            if (response.isSuccessful) {
                response.body() // 성공 시 ChallengeCreationResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun deleteChallenge(challengeId: Long): ChallengeDeletionResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.deleteChallenge(challengeId)

            if (response.isSuccessful) {
                response.body() // 성공 시 ChallengeDeletionResponse 반환
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


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


    private fun getCurrentDateTime(): String {
        //TODO: 나중에 이거 적당한 위치에 잚 만들어놓기
        return "2024-09-05:07:25"
    }
}
