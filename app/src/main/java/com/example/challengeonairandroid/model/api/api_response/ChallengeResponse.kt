package com.example.challengeonairandroid.model.api.api_response

import com.google.gson.annotations.SerializedName

data class ChallengeResponse(
    @SerializedName("challenge_name") val challengeName: String,
    @SerializedName("challenge_body") val challengeBody: String,
    @SerializedName("point") val point: Int,
    @SerializedName("challenge_date") val challengeDate: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("min_participant_num") val minParticipantNum: Int,
    @SerializedName("max_participant_num") val maxParticipantNum: Int,
    @SerializedName("current_participant_num") val currentParticipantNum: Int,
    @SerializedName("host_id") val hostId: String
)

data class ChallengeCategoryResponse(
    @SerializedName("challenges") val challenges: List<HistoryChallengeResponse>
)

data class ChallengeCreationRequest(
    @SerializedName("host_id") val hostId: Long,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("challenge_name") val challengeName: String,
    @SerializedName("challenge_body") val challengeBody: String? = null,
    @SerializedName("point") val point: Int,
    @SerializedName("challenge_date") val challengeDate: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("max_participant_num") val maxParticipantNum: Int,
    @SerializedName("min_participant_num") val minParticipantNum: Int
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