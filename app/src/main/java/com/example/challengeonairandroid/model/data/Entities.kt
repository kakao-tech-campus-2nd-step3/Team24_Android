package com.example.challengeonairandroid.model.data

data class History(
    val challengeName: String,
    val challengeStartTime: String,
    val challengeEndTime: String,
    val historyDate: String,
    val isSucceed: Boolean,
    val isHost: Boolean
)

data class User(
    val userId: Long,
    val userName: String,
)

data class UserProfile(
    val userNickName: String,
    val userBody: String,
    val imageUrl: String,
    val point: Int
)

data class Category(
    val categoryId: Long,
    val categoryName: String,
    val categoryDescription: String
) // 카테고리 아이디를 받으면 enum으로

data class Challenge(
    val challengeName: String,
    val imageUrl: String,
//    val isHost: Boolean, // 이거 없어도 될 듯?
    val hostId: Long
)

data class Participant(
    val participantId: Long,
    val userId: Long,
    val challengeId: Long
)