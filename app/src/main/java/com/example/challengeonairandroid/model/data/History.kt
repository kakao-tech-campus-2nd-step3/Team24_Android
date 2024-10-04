package com.example.challengeonairandroid.model.data

data class History(
    val historyID: Long,
    val userID: Long,
    val challengeID: Long,
    val isSucced: Boolean,
    val isHost: Boolean
)