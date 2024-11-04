package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _challengeDetailUiState = MutableStateFlow(ChallengeDetailUiState())
    val challengeDetailUiState = _challengeDetailUiState.asStateFlow()

    data class ChallengeDetailUiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        // Challenge 상세 정보
        val challengeId: Long = 0,
        val challengeName: String = "",
        val challengeBody: String = "",
        val point: Int = 0,
        val challengeDate: String = "",
        val startTime: String = "",
        val endTime: String = "",
        val imageExtension: String = "",
        val minParticipantNum: Int = 0,
        val maxParticipantNum: Int = 0,
        val currentParticipantNum: Int = 0,
        val hostId: Long = 0,
        val categoryId: Int = 0
    )

    fun getChallengeDetails(accessToken: String, challengeId: Long, date: String) {
        viewModelScope.launch {
            _challengeDetailUiState.update { it.copy(isLoading = true) }
            try {
                val response = repository.getChallengeDetails(accessToken, challengeId, date)
                if (response.isSuccessful && response.body() != null) {
                    val challenge = response.body()!!
                    _challengeDetailUiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            challengeId = challenge.challengeId,
                            challengeName = challenge.challengeName,
                            challengeBody = challenge.challengeBody,
                            point = challenge.point,
                            challengeDate = challenge.challengeDate,
                            startTime = challenge.startTime,
                            endTime = challenge.endTime,
                            imageExtension = challenge.imageExtension,
                            minParticipantNum = challenge.minParticipantNum,
                            maxParticipantNum = challenge.maxParticipantNum,
                            currentParticipantNum = challenge.currentParticipantNum,
                            hostId = challenge.hostId,
                            categoryId = challenge.categoryId
                        )
                    }
                } else {
                    _challengeDetailUiState.update {
                        it.copy(
                            isLoading = false,
                            error = "챌린지 정보를 불러오는데 실패했습니다."
                        )
                    }
                }
            } catch (e: Exception) {
                _challengeDetailUiState.update {
                    it.copy(
                        isLoading = false,
                        error = "오류가 발생했습니다: ${e.message}"
                    )
                }
            }
        }
    }
}