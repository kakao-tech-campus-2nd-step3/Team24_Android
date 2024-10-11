package com.example.challengeonairandroid.model.api.response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("user_nick_name") val userNickName: String,
    @SerializedName("user_body") val userBody: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("point") val point: Int
)

data class UserProfileUpdateRequest(
    @SerializedName("user_nick_name") val userNickName: String? = null,
    @SerializedName("user_body") val userBody: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null
)

data class UserProfileUpdateResponse(
    @SerializedName("user_id") val userId: Long
)