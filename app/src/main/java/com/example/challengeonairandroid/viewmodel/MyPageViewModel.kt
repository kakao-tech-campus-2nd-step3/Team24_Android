package com.example.challengeonairandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.api.response.HistoryListResponse
import com.example.challengeonairandroid.model.api.response.HistoryResponse
import com.example.challengeonairandroid.model.api.response.UserProfileResponse
import com.example.challengeonairandroid.model.api.response.UserProfileUpdateRequest
import com.example.challengeonairandroid.model.data.Challenge
import com.example.challengeonairandroid.model.data.History
import com.example.challengeonairandroid.model.data.UserProfile
import com.example.challengeonairandroid.model.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private val dummyHistoryListResponse = HistoryListResponse(
    histories = listOf(
        HistoryResponse(
            challenge = ChallengeResponse(
                challengeName = "아침 운동 챌린지",
                challengeBody = "매일 아침 30분 운동하기",
                point = 100,
                challengeDate = "2024-03-01",
                startTime = "06:00",
                endTime = "07:00",
                imageUrl = "https://example.com/morning_exercise.jpg",
                minParticipantNum = 5,
                maxParticipantNum = 20,
                currentParticipantNum = 12,
                hostId = "user123"
            ),
            isSucceed = true,
            isHost = false
        ),
        HistoryResponse(
            challenge = ChallengeResponse(
                challengeName = "독서 챌린지",
                challengeBody = "한 달 동안 5권 책 읽기",
                point = 150,
                challengeDate = "2024-03-15",
                startTime = "00:00",
                endTime = "23:59",
                imageUrl = "https://example.com/reading_challenge.jpg",
                minParticipantNum = 10,
                maxParticipantNum = 50,
                currentParticipantNum = 25,
                hostId = "user456"
            ),
            isSucceed = false,
            isHost = true
        )
    )
)

private val dummyUserProfileResponse = UserProfileResponse(
    userNickName = "챌린지마스터",
    userBody = "건강한 삶을 위해 매일 노력합니다!",
    imageUrl = "https://example.com/profile_image.jpg",
    point = 1000
)

private val dummyChallengeResponses: List<ChallengeResponse> = listOf(
    ChallengeResponse(
        challengeName = "아침 운동 챌린지",
        challengeBody = "매일 아침 30분 운동하기",
        point = 100,
        challengeDate = "2024-03-01",
        startTime = "06:00",
        endTime = "07:00",
        imageUrl = "https://picsum.photos/200/300",
        minParticipantNum = 5,
        maxParticipantNum = 20,
        currentParticipantNum = 12,
        hostId = "user123"
    ),
    ChallengeResponse(
        challengeName = "독서 챌린지",
        challengeBody = "한 달 동안 5권 책 읽기",
        point = 150,
        challengeDate = "2024-03-15",
        startTime = "00:00",
        endTime = "23:59",
        imageUrl = "https://picsum.photos/200/300",
        minParticipantNum = 10,
        maxParticipantNum = 50,
        currentParticipantNum = 25,
        hostId = "user456"
    ),
    ChallengeResponse(
        challengeName = "물 마시기 챌린지",
        challengeBody = "하루 2리터 물 마시기",
        point = 80,
        challengeDate = "2024-04-01",
        startTime = "08:00",
        endTime = "22:00",
        imageUrl = "https://picsum.photos/200/300",
        minParticipantNum = 3,
        maxParticipantNum = 100,
        currentParticipantNum = 75,
        hostId = "user789"
    ),
    ChallengeResponse(
        challengeName = "코딩 스터디 챌린지",
        challengeBody = "매일 2시간 코딩 공부하기",
        point = 200,
        challengeDate = "2024-04-15",
        startTime = "19:00",
        endTime = "21:00",
        imageUrl = "https://picsum.photos/200/300",
        minParticipantNum = 5,
        maxParticipantNum = 15,
        currentParticipantNum = 8,
        hostId = "user101"
    ),
    ChallengeResponse(
        challengeName = "환경 보호 챌린지",
        challengeBody = "일주일 동안 일회용품 사용 줄이기",
        point = 120,
        challengeDate = "2024-05-01",
        startTime = "00:00",
        endTime = "23:59",
        imageUrl = "https://picsum.photos/200/300",
        minParticipantNum = 20,
        maxParticipantNum = 200,
        currentParticipantNum = 150,
        hostId = "user202"
    )
)

//TODO: 에러 처리
//TODO: accessToken을 뷰모델 생성자를 통해 주입
@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _histories = MutableStateFlow<List<History>>(emptyList())
    val histories: StateFlow<List<History>> = _histories.asStateFlow()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    private val _challenges = MutableStateFlow<List<Challenge>>(emptyList())
    val challenges: StateFlow<List<Challenge>> = _challenges.asStateFlow()

    private val accessToken = "accessToken"

    private val _successChallengeNum = MutableStateFlow<Int>(0)
    val successChallengeNum: StateFlow<Int> = _successChallengeNum.asStateFlow()

    private val _tryChallengeNum = MutableStateFlow<Int>(0)
    val tryChallengeNum: StateFlow<Int> = _tryChallengeNum.asStateFlow()

    private val _myCreatedChallengeNum = MutableStateFlow<Int>(0)
    val myCreatedChallengeNum: StateFlow<Int> = _myCreatedChallengeNum.asStateFlow()

    init {
        loadAllHistories()
        loadUserData()
        loadWaitingChallenges()
    }

    private fun loadAllHistories() {
        viewModelScope.launch {
            val historyListResponse = dummyHistoryListResponse
//            val historyListResponse = myPageRepository.getAllHistory(accessToken)
            historyListResponse?.let { response ->
                updateHistories(response.histories)
            }
            Log.d("histories", "loadAllHistories: ${_histories.value}")
        }
    }

    private fun updateHistories(histories: List<HistoryResponse>) {
        _histories.value = histories.map { historyResponse ->
            History(
                challengeName = historyResponse.challenge.challengeName,
                challengeStartTime = historyResponse.challenge.startTime,
                challengeEndTime = historyResponse.challenge.endTime,
                isSucceed = historyResponse.isSucceed,
                isHost = historyResponse.isHost
            )
        }
        val successCount = histories.count { it.isSucceed }
        _successChallengeNum.value = successCount
        val tryCount = histories.size
        _tryChallengeNum.value = tryCount
        val myCreateCount = histories.count { it.isHost }
        _myCreatedChallengeNum.value = myCreateCount
    }

    private fun loadUserData() {
        viewModelScope.launch {
            val userProfileResponse = dummyUserProfileResponse
//            val userProfileResponse = myPageRepository.getUserProfile(accessToken)
            userProfileResponse?.let { response ->
                updateUserProfile(response)
            }
            Log.d("userProfiles", "loadUserData: ${_userProfile.value}")
        }
    }

    private fun updateUserProfile(response: UserProfileResponse) {
        _userProfile.value = UserProfile(
            userNickName = response.userNickName,
            userBody = response.userBody,
            imageUrl = response.imageUrl,
            point = response.point
        )
    }

    private fun loadWaitingChallenges() {
        viewModelScope.launch {
            val challengeResponses = dummyChallengeResponses // TODO: API 만들어지면 교체
//            val challengeResponse = myPageRepository.
            updateChallenges(challengeResponses)
        }
    }

    private fun updateChallenges(challengeResponses: List<ChallengeResponse>) {
        _challenges.value = challengeResponses.map { response ->
            Challenge(
                challengeName = response.challengeName,
                imageUrl = response.imageUrl,
                hostId = response.hostId.toLongOrNull() ?: 0L
            )
        }
    }

    fun updateUserProfile(userProfileUpdateRequest: UserProfileUpdateRequest) {
        viewModelScope.launch {
            _isUpdating.value = true
            try {
                val result = myPageRepository.updateUserProfile(userProfileUpdateRequest, accessToken)
                if (result != null) {
                    // 업데이트 성공
                    _updateResult.value = UpdateResult.Success
                    // 업데이트된 프로필 정보로 UI 갱신
                    loadUserData()
                } else {
                    // 업데이트 실패
                    _updateResult.value = UpdateResult.Failure("프로필 업데이트에 실패했습니다.")
                }
            } catch (e: Exception) {
                // 예외 발생
                _updateResult.value = UpdateResult.Failure("오류가 발생했습니다: ${e.message}")
            } finally {
                _isUpdating.value = false
            }
        }
    }
}

