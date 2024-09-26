package com.example.challengeonairandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeonairandroid.model.repository.LoginRepository
import com.example.challengeonairandroid.model.repository.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val userInfo: UserInfo) : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
        private val _loginState = MutableLiveData<LoginState>()
        val loginState: LiveData<LoginState> = _loginState

        fun initiateLogin() {
            loginRepository.initiateKakaoLogin()
        }

        fun handleLoginCallback(url: String) {
            viewModelScope.launch {
                try {
                    loginRepository.handleLoginCallback(url)
                    // 로그인 성공 후 사용자 정보 가져오기
                    val userInfo = UserInfo("Kim","12345")
                    _loginState.value = LoginState.Success(userInfo)
                } catch (e: Exception) {
                    _loginState.value = LoginState.Error(e.message ?: "Login failed")
                }
            }
        }
}