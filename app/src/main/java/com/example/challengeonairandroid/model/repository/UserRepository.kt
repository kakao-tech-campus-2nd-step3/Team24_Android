package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.response.LogoutResponse
import com.example.challengeonairandroid.model.api.response.UserDeletionResponse
import com.example.challengeonairandroid.model.api.response.UserRegistrationRequest
import com.example.challengeonairandroid.model.api.response.UserRegistrationResponse
import com.example.challengeonairandroid.model.api.service.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi
) {
    sealed class UserResult<out T> {
        data class Success<T>(val data: T) : UserResult<T>()
        data class Error(val message: String, val code: Int? = null) : UserResult<Nothing>()
    }

    suspend fun registerUser(
        userName: String,
        userNickName: String,
        email: String,
        password: String
    ): UserResult<UserRegistrationResponse> {
        return try {
            val request = UserRegistrationRequest(
                userName = userName,
                userNickName = userNickName,
                email = email,
                password = password
            )
            val response = userApi.registerUser(request)

            if (response.isSuccessful) {
                response.body()?.let {
                    UserResult.Success(it)
                } ?: UserResult.Error("Registration failed: Empty response")
            } else {
                UserResult.Error(
                    message = response.errorBody()?.string() ?: "Registration failed",
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            UserResult.Error("Registration failed: ${e.message}")
        }
    }

    suspend fun logout(accessToken: String): UserResult<LogoutResponse> {
        return try {
            val response = userApi.logout(accessToken)

            if (response.isSuccessful) {
                response.body()?.let {
                    UserResult.Success(it)
                } ?: UserResult.Error("Logout failed: Empty response")
            } else {
                UserResult.Error(
                    message = response.errorBody()?.string() ?: "Logout failed",
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            UserResult.Error("Logout failed: ${e.message}")
        }
    }

    suspend fun deleteUser(accessToken: String): UserResult<UserDeletionResponse> {
        return try {
            val response = userApi.deleteUser(accessToken)

            if (response.isSuccessful) {
                response.body()?.let {
                    UserResult.Success(it)
                } ?: UserResult.Error("Account deletion failed: Empty response")
            } else {
                UserResult.Error(
                    message = response.errorBody()?.string() ?: "Account deletion failed",
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            UserResult.Error("Account deletion failed: ${e.message}")
        }
    }
}