package com.example.challengeonairandroid.model.data

data class History(
    val challengeName: String,
    val challengeStartTime: String,
    val challengeEndTime: String,
    val challengeCategory: String,
    val isSucceed: Boolean,
    val isHost: Boolean
)

data class User(
    val userId: Long,
    val userName: String,
)

data class UserProfile(
    val profileId: Long,
    val userId: Long,
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
    val challengeId: Long,
    val categoryId: Long,
    val hostId: Long,
    val challengeName: String,
    val challengeBody: String,
    val challengePoint: Int,
    val challengeDate: String,
    val startTime: String,
    val endTime: String,
    val imageUrl: String,
    val minParticipantNum: Int,
    val maxParticipantNum: Int
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
    challengeCategory = "운동",
    isSucceed = true,
    isHost = false
)

val dummyUserProfile = UserProfile(
    profileId = 1L,
    userId = 12345L,
    userNickName = "김미녁",
    userBody = "건강한 삶을 추구하는 운동 매니아입니다!",
    imageUrl = "https://example.com/profile.jpg",
    point = 150
)

val dummyChallenges = listOf(
    Challenge(
        challengeId = 1L,
        categoryId = 1L,
        hostId = 1001L,
        challengeName = "아침 조깅 챌린지",
        challengeBody = "매일 아침 30분 조깅하기",
        challengePoint = 50,
        challengeDate = "2024-10-10",
        startTime = "06:00",
        endTime = "06:30",
        imageUrl = "https://example.com/jogging_challenge.jpg",
        minParticipantNum = 2,
        maxParticipantNum = 10
    ),
    Challenge(
        challengeId = 2L,
        categoryId = 2L,
        hostId = 1002L,
        challengeName = "독서 챌린지",
        challengeBody = "한 달에 5권 책 읽기",
        challengePoint = 70,
        challengeDate = "2024-10-15",
        startTime = "00:00",
        endTime = "23:59",
        imageUrl = "https://example.com/reading_challenge.jpg",
        minParticipantNum = 1,
        maxParticipantNum = 20
    ),
    Challenge(
        challengeId = 3L,
        categoryId = 3L,
        hostId = 1003L,
        challengeName = "DIY 프로젝트 챌린지",
        challengeBody = "자신만의 DIY 프로젝트 만들기",
        challengePoint = 100,
        challengeDate = "2024-10-20",
        startTime = "09:00",
        endTime = "17:00",
        imageUrl = "https://example.com/diy_challenge.jpg",
        minParticipantNum = 3,
        maxParticipantNum = 15
    )
)
