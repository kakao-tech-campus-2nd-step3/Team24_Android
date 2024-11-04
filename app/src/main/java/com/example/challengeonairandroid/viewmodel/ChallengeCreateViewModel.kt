package com.example.challengeonairandroid.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.api.response.ChallengeCreationRequest
import com.example.challengeonairandroid.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeCreateViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _challengeCreateUiState = MutableStateFlow(ChallengeCreateUiState())
    val challengeCreateUiState = _challengeCreateUiState.asStateFlow()

    data class ChallengeCreateUiState(
        // 입력 필드 상태
        val hostId: Long = 0,  // 내 id
        val categoryId: Int = 0, // 버튼으로 선택받기
        val challengeName: String = "", // 텍스트필드로 입력받기
        val challengeBody: String = "", // 텍스트필드로 입력받기
        val point: String = "",  // 텍스트필드로 입력받기
        val challengeDate: String = "", // 캘린더로 입력받기
        val startTime: String = "", // 타임피커로 입력받기
        val endTime: String = "", // 타임피커로 입력받기
        val imageExtension: String? = null, // 사진 업로드받기
        val minParticipants: String = "", // 버튼으로 조절
        val maxParticipants: String = "", // 버튼으로 조절

        // 유효성 검사 상태
        val isChallengeNameValid: Boolean = true,
        val isChallengeBodyValid: Boolean = true,
        val isPointValid: Boolean = true,
        val isDateValid: Boolean = true,
        val isTimeValid: Boolean = true,
        val isParticipantsValid: Boolean = true,
        val isCategorySelected: Boolean = false,

        // 에러 메시지
        val challengeNameError: String? = null,
        val pointError: String? = null,
        val dateError: String? = null,
        val timeError: String? = null,
        val participantsError: String? = null,

        // 로딩 및 전체 상태
        val isLoading: Boolean = false,
        val error: String? = null,
        val isSuccess: Boolean = false
    )

    // 입력값 업데이트 함수들
    fun updateChallengeName(name: String) {
        _challengeCreateUiState.update { currentState ->
            currentState.copy(
                challengeName = name,
                isChallengeNameValid = name.isNotBlank(),
                challengeNameError = if (name.isBlank()) "제목을 입력해주세요" else null
            )
        }
    }

    fun updatePoint(point: String) {
        _challengeCreateUiState.update { currentState ->
            currentState.copy(
                point = point,
                isPointValid = point.toIntOrNull() != null && (point.toIntOrNull() ?: 0) > 0,
                pointError = when {
                    point.isBlank() -> "포인트를 입력해주세요"
                    point.toIntOrNull() == null -> "숫자만 입력해주세요"
                    (point.toIntOrNull() ?: 0) <= 0 -> "0보다 큰 값을 입력해주세요"
                    else -> null
                }
            )
        }
    }

    fun updateDateTime(date: String, start: String, end: String) {
        _challengeCreateUiState.update { currentState ->
            currentState.copy(
                challengeDate = date,
                startTime = start,
                endTime = end,
                isTimeValid = start < end,
                timeError = if (start >= end) "시작 시간이 종료 시간보다 빨라야 합니다" else null
            )
        }
    }

    fun updateParticipants(min: String, max: String) {
        _challengeCreateUiState.update { currentState ->
            val minNum = min.toIntOrNull() ?: 0
            val maxNum = max.toIntOrNull() ?: 0
            currentState.copy(
                minParticipants = min,
                maxParticipants = max,
                isParticipantsValid = minNum > 0 && maxNum >= minNum,
                participantsError = when {
                    min.isEmpty() || max.isEmpty() -> "참가 인원을 입력해주세요"
                    maxNum < minNum -> "최대 인원은 최소 인원보다 커야 합니다"
                    else -> null
                }
            )
        }
    }

    fun updateImage(extension: String) {
        _challengeCreateUiState.update { currentState ->
            currentState.copy(
                imageExtension = extension
            )
        }
    }

    fun createChallenge(accessToken: String) {
        if (!isInputValid()) return

        viewModelScope.launch {
            _challengeCreateUiState.update { it.copy(isLoading = true) }
            try {
                val request = ChallengeCreationRequest(
                    hostId = challengeCreateUiState.value.hostId,
                    categoryId = challengeCreateUiState.value.categoryId,
                    challengeName = challengeCreateUiState.value.challengeName,
                    challengeBody = challengeCreateUiState.value.challengeBody,
                    point = challengeCreateUiState.value.point.toInt(),
                    challengeDate = challengeCreateUiState.value.challengeDate,
                    startTime = challengeCreateUiState.value.startTime,
                    endTime = challengeCreateUiState.value.endTime,
                    imageExtension = challengeCreateUiState.value.imageExtension,
                    maxParticipantNum = challengeCreateUiState.value.maxParticipants.toInt(),
                    minParticipantNum = challengeCreateUiState.value.minParticipants.toInt()
                )

                val response = repository.createChallenge(accessToken, request)
                if (response.isSuccessful) {
                    _challengeCreateUiState.update { it.copy(isSuccess = true) }
                } else {
                    _challengeCreateUiState.update { it.copy(error = "챌린지 생성에 실패했습니다") }
                }
            } catch (e: Exception) {
                _challengeCreateUiState.update { it.copy(error = "오류가 발생했습니다: ${e.message}") }
            } finally {
                _challengeCreateUiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun isInputValid(): Boolean {
        return challengeCreateUiState.value.run {
            isChallengeNameValid &&
                    isPointValid &&
                    isDateValid &&
                    isTimeValid &&
                    isParticipantsValid &&
                    isCategorySelected
        }
    }
}