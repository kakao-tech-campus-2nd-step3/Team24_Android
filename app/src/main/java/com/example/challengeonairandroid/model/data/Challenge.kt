package com.example.challengeonairandroid.model.data

data class Challenge(
    val hostId: Long,
    val challengeId: Long,
    val categoryId: Int,
    var challengeImgUrl: String,
    var challengeName: String,
    val challengeBody: String,
    val challengePoint: Int,
    val challengeDate: String,
    val startTime: String,
    val endTime: String,
    val maxParticipantNum: Int,
    val minParticipantNum: Int
)
