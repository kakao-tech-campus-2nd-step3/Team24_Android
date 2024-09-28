package com.example.challengeonairandroid.model.data

data class Challenge(
    val hostId: Long,
    val challengeId: Long,
    val categoryId: Int? = null,
    var challengeImgUrl: String? = null,
    var challengeName: String,
    val challengeBody: String? = null,
    val challengePoint: Int,
    val challengeDate: String,
    val startTime: String,
    val endTime: String,
    val maxParticipantNum: Int,
    val minParticipantNum: Int
)
