package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRepository @Inject constructor(private val apiService: ApiService) {

    data class UserProfileUpdateRequest(
        val userNickName: String? = null,
        val userBody: String? = null,
        val imageUrl: String? = null
    )

    data class UserProfileUpdateResponse(
        val userId: Long
    )

    suspend fun updateUserProfile(
        accessToken: String,
        userNickName: String? = null,
        userBody: String? = null,
        imageUrl: String? = null
    ): Response<UserProfileUpdateResponse> {
        val updateRequest = UserProfileUpdateRequest(
            userNickName = userNickName,
            userBody = userBody,
            imageUrl = imageUrl
        )

        return apiService.post(
            url = "api/userprofile",
            body = updateRequest
        ).let { response ->
            if (response.isSuccessful) {
                val userId = response.body()?.toLongOrNull()
                if (userId != null) {
                    Response.success(UserProfileUpdateResponse(userId))
                } else {
                    Response.error(response.code(), response.errorBody()!!)
                }
            } else {
                Response.error(response.code(), response.errorBody()!!)
            }
        }
    }
}