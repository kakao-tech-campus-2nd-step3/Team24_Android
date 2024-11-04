package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class AllChallengesResponse(
    @SerializedName("challenges") val challenges: List<ChallengeResponse>
) // 아직 백엔드에서 안 만들어졌음

data class ChallengeResponse(
    @SerializedName("challengeId") val challengeId: Long,
    @SerializedName("challengeName") val challengeName: String,
    @SerializedName("challengeBody") val challengeBody: String,
    @SerializedName("point") val point: Int,
    @SerializedName("challengeDate") val challengeDate: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("imageExtension") val imageExtension: String,
    @SerializedName("minParticipantNum") val minParticipantNum: Int,
    @SerializedName("maxParticipantNum") val maxParticipantNum: Int,
    @SerializedName("currentParticipantNum") val currentParticipantNum: Int,
    @SerializedName("hostId") val hostId: Long,
    @SerializedName("categoryId") val categoryId: Int
)

data class ChallengeCategoryResponse(
    @SerializedName("challenges") val challenges: List<ChallengeResponse>
)

data class ChallengeCreationRequest(
    @SerializedName("hostId") val hostId: Long,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("challengeName") val challengeName: String,
    @SerializedName("challengeBody") val challengeBody: String? = null,
    @SerializedName("point") val point: Int,
    @SerializedName("challengeDate") val challengeDate: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("imageExtension") val imageExtension: String? = null,
    @SerializedName("maxParticipantNum") val maxParticipantNum: Int,
    @SerializedName("minParticipantNum") val minParticipantNum: Int
)

data class ChallengeCreationResponse(
    @SerializedName("challenge_id") val challengeId: Long
)

data class ChallengeDeletionResponse(
    @SerializedName("challenge_id") val challengeId: Long
)

data class ChallengeReservationResponse(
    @SerializedName("challenge_id") val challengeId: Long,
    @SerializedName("user_id") val userId: Long
)