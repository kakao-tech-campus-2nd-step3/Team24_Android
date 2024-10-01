package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("email") val email: String? = null,
    @SerializedName("msg") val message: String? = null,
    @SerializedName("user_id") val userId: Long? = null
)

data class UserErrorResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val message: String? = null,
    @SerializedName("validation") val validation: ValidationErrors? = null
)

data class ValidationErrors(
    @SerializedName("user_name") val userName: String? = null,
    @SerializedName("user_nick_name") val userNickName: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("email") val email: String? = null
)

// 회원가입 요청 모델
data class RegisterRequest(
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_nick_name") val userNickName: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String
)

// 로그인 응답 모델 (토큰 정보를 포함할 경우)
data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)