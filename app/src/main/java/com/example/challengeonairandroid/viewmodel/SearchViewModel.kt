package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.api.response.ChallengeResponse
import com.example.challengeonairandroid.model.repository.Repository
import com.example.challengeonairandroid.model.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // 전체 챌린지 리스트 (원본 데이터)
    private val _allChallenges = MutableStateFlow<List<ChallengeResponse>>(emptyList())

    // 검색 결과 리스트
    private val _searchResults = MutableStateFlow<List<ChallengeResponse>>(emptyList())
    val searchResults: StateFlow<List<ChallengeResponse>> = _searchResults.asStateFlow()

    // 현재 검색어
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // 초기 데이터 로드
    fun loadAllChallenges(accessToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllChallenges(accessToken)
                if (response.isSuccessful) {
                    _allChallenges.value = response.body()?.challenges ?: emptyList()
                    // 초기에는 검색 결과도 전체 리스트로 설정
                    _searchResults.value = _allChallenges.value
                }
            } catch (e: Exception) {
                // 에러 처리
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 검색어로 필터링
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        // 검색어가 비어있으면 전체 리스트 표시
        if (query.isEmpty()) {
            _searchResults.value = _allChallenges.value
            return
        }
        // 검색어로 필터링
        _searchResults.value = _allChallenges.value.filter { challenge ->
            challenge.challengeName.contains(query, ignoreCase = true) ||
                    challenge.challengeBody.contains(query, ignoreCase = true)
        }
    }

    // 챌린지 상세 정보 조회
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