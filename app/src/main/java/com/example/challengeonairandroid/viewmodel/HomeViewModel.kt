package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.repository.HomeRepository
import com.example.challengeonairandroid.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _challengesList = MutableStateFlow<List<ChallengeResponse>>(emptyList())
    val challengesList: StateFlow<List<ChallengeResponse>> = _challengesList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadAllChallenges(accessToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllChallenges(accessToken)
                if (response.isSuccessful) {
                    _challengesList.value = response.body()?.challenges ?: emptyList()
                }
            } catch (e: Exception) {
                // 에러 처리
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getChallengeDetails(accessToken: String, challengeId: Long, date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getChallengeDetails(accessToken, challengeId, date)
                if (response.isSuccessful) {
                    // 상세 정보 처리
                }
            } catch (e: Exception) {
                // 에러 처리
            } finally {
                _isLoading.value = false
            }
        }
    }
}