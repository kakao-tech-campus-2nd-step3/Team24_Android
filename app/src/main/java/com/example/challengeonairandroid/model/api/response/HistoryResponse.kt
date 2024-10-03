package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("challenge") val challenge: ChallengeResponse,
    @SerializedName("is_succeed") val isSucceed: Boolean,
    @SerializedName("is_host") val isHost: Boolean
)

data class HistoryListResponse(
    @SerializedName("histories") val histories: List<HistoryResponse>
)