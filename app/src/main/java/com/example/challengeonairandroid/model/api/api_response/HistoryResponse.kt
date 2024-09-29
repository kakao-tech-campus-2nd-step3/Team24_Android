package com.example.challengeonairandroid.model.api.api_response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("challenge") val challenge: HistoryChallengeResponse,
    @SerializedName("is_succeed") val isSucceed: Boolean,
    @SerializedName("is_host") val isHost: Boolean
)

data class HistoryChallengeResponse(
    @SerializedName("challenge_name") val challengeName: String,
    @SerializedName("challenge_body") val challengeBody: String,
    @SerializedName("point") val point: Int,
    @SerializedName("challenge_date") val challengeDate: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("participant_num") val participantNum: Int
)

data class HistoryListResponse(
    @SerializedName("histories") val histories: List<HistoryResponse>
)