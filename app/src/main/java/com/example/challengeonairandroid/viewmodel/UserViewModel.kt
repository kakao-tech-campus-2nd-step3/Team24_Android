package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.api.response.LogoutResponse
import com.example.challengeonairandroid.model.api.response.UserDeletionResponse
import com.example.challengeonairandroid.model.api.response.UserRegistrationRequest
import com.example.challengeonairandroid.model.api.response.UserRegistrationResponse
import com.example.challengeonairandroid.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    // UI State
    private val _userState = MutableStateFlow<UserState>(UserState.Initial)
    val userState = _userState.asStateFlow()

    // User Actions
    fun registerUser(request: UserRegistrationRequest) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = repository.registerUser(request)
                if (response.isSuccessful && response.body() != null) {
                    _userState.value = UserState.RegisterSuccess(response.body()!!)
                } else {
                    _userState.value = UserState.Error("회원가입 실패: ${response.message()}")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("회원가입 중 오류 발생: ${e.message}")
            }
        }
    }

    fun logout(accessToken: String) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = repository.logout(accessToken)
                if (response.isSuccessful && response.body() != null) {
                    _userState.value = UserState.LogoutSuccess(response.body()!!)
                } else {
                    _userState.value = UserState.Error("로그아웃 실패: ${response.message()}")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("로그아웃 중 오류 발생: ${e.message}")
            }
        }
    }

    fun deleteUser(accessToken: String) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = repository.deleteUser(accessToken)
                if (response.isSuccessful && response.body() != null) {
                    _userState.value = UserState.DeleteSuccess(response.body()!!)
                } else {
                    _userState.value = UserState.Error("회원탈퇴 실패: ${response.message()}")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("회원탈퇴 중 오류 발생: ${e.message}")
            }
        }
    }

    // UI State를 나타내는 sealed class
    sealed class UserState {
        object Initial : UserState()
        object Loading : UserState()
        data class RegisterSuccess(val data: UserRegistrationResponse) : UserState()
        data class LogoutSuccess(val data: LogoutResponse) : UserState()
        data class DeleteSuccess(val data: UserDeletionResponse) : UserState()
        data class Error(val message: String) : UserState()
    }
}