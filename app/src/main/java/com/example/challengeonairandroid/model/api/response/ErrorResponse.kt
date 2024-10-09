package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val message: String
)