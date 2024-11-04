package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    @SerializedName("userName") val userName: String,
    @SerializedName("userNickName") val userNickName: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String
)

data class UserRegistrationResponse(
    @SerializedName("email") val email: String
)

data class LogoutResponse(
    @SerializedName("msg") val message: String
)

data class UserDeletionResponse(
    @SerializedName("userId") val userId: Long
)