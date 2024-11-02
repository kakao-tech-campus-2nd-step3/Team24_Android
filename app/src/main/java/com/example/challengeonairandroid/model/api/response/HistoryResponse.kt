package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class ChallengeHistoryResponse(
    @SerializedName("challenge_name") val challengeName: String,
    @SerializedName("challenge_date") val challengeDate: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("participantNum") val participantNum: Int
)

data class HistoryResponse(
    @SerializedName("challenge") val challenge: ChallengeResponse,
    @SerializedName("isSucceeded") val isSucceeded: Boolean,
    @SerializedName("isHost") val isHost: Boolean,
    @SerializedName("point") val point: Int
)

data class HistoryListResponse(
    @SerializedName("histories") val histories: List<HistoryResponse>
)