package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.data.Category
import com.example.challengeonairandroid.model.data.Challenge
import com.example.challengeonairandroid.model.data.History
import com.example.challengeonairandroid.model.data.UserProfile
import com.example.challengeonairandroid.model.data.dummyChallenges
import com.example.challengeonairandroid.model.data.dummyHistory
import com.example.challengeonairandroid.model.data.dummyUserProfile
import com.example.challengeonairandroid.model.repository.ChallengeRepository
import com.example.challengeonairandroid.model.repository.MyPageRepository
import com.example.challengeonairandroid.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _histories = MutableLiveData<List<History>>()
    val histories: LiveData<List<History>> = _histories

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    private val _waitingChallenges = MutableLiveData<List<Challenge>>()
    val waitingChallenge: LiveData<List<Challenge>> = _waitingChallenges

    private val accessToken = "accessToken"

    init {
        loadAllHistories()
        loadUserData()
        loadWaitingChallenges()
    }

    private fun loadAllHistories() {
        viewModelScope.launch {
            val historyListResponse = myPageRepository.getAllHistory(accessToken)
            historyListResponse?.let { response ->
                _histories.value = List(response.histories.size) {
                    dummyHistory
                }
            }
        }
    }

    private fun loadUserData() {
        viewModelScope.launch {
            val userProfileResponse = myPageRepository.getUserProfile(accessToken)
            userProfileResponse?.let {
                _userProfile.value = dummyUserProfile
            }
        }
    }

    private fun loadWaitingChallenges() {
        viewModelScope.launch {
            _waitingChallenges.value = dummyChallenges
        }
    }
}