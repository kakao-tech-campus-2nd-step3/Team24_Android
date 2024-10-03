package com.example.challengeonairandroid.model.data

data class History(
    val challengeName: String,
    val challengeStartTime: String,
    val challengeEndTime: String,
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

val dummyHistory = History(
    challengeName = "아침 조깅 챌린지",
    challengeStartTime = "2024-09-01T06:00:00",
    challengeEndTime = "2024-09-01T07:00:00",
    isSucceed = true,
    isHost = false
)

val dummyUserProfile = UserProfile(
    userNickName = "김미녁",
    userBody = "건강한 삶을 추구하는 운동 매니아입니다!",
    imageUrl = "https://example.com/profile.jpg",
    point = 150
)
