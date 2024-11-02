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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Singleton
class ChallengeRepository @Inject constructor(
    private val challengeApi: ChallengeApi
) {
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

    suspend fun getChallengesByCategory(categoryId: Int): ChallengeCategoryResponse? = withContext(Dispatchers.IO) {
        try {
            val date = getCurrentDateTime()
            val response = challengeApi.getChallengesByCategory("", categoryId, date)

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

    suspend fun createChallenge(challenge: ChallengeCreationRequest): ChallengeCreationResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.createChallenge("", challenge)

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

    suspend fun deleteChallenge(challengeId: Long): ChallengeDeletionResponse? = withContext(Dispatchers.IO) {
        try {
            val response = challengeApi.deleteChallenge("", challengeId)

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