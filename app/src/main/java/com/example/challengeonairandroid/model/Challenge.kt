package com.example.challengeonairandroid.model

data class Challenge(
    val challengeId: Long,
    val hostId: Long,
    val categoryId: Int? = null,
    val challengeName: String,
    val challengeBody: String? = null,
    val point: Int,
    val challengeDate: String,
    val startTime: String,
    val endTime: String,
    val imageUrl: String,
    val maxParticipantNum: Int,
    val minParticipantNum: Int
)
