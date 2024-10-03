package com.example.challengeonairandroid.model.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

data class UserInfo(val name: String, val uid: String)

@Serializable
data class ReissueTokenRequest(val refreshToken: String)

@Serializable
data class ReissueTokenResponse(val accessToken: String, val refreshToken: String)

@Singleton
class LoginRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val KAKAO_LOGIN_URL = "/oauth2/authorization/kakao"
        private const val REISSUE_URL = "/reissue"
        private const val PREF_NAME = "AuthPrefs"
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
    }

    fun initiateKakaoLogin() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(KAKAO_LOGIN_URL)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

    suspend fun handleLoginCallback(url: String): Result<UserInfo> = withContext(Dispatchers.IO) {
        try {
            val uri = Uri.parse(url)
            val accessToken = uri.getQueryParameter("access_token")
            val refreshToken = uri.getQueryParameter("refresh_token")

            if (accessToken != null && refreshToken != null) {
                saveTokens(accessToken, refreshToken)
                //TODO:사용자 정보 가져오는 API 호출해야함
                val userInfo = UserInfo("Test User", "12345") //dummy UserInfo
                Result.success(userInfo)
            } else {
                Result.failure(Exception("Login failed: Tokens not found in URL"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun saveTokens(accessToken: String, refreshToken: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().apply {
            putString(ACCESS_TOKEN_KEY, accessToken)
            putString(REFRESH_TOKEN_KEY, refreshToken)
            apply()
        }
    }

    private fun getAccessToken(): String? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(ACCESS_TOKEN_KEY, null)

    private fun getRefreshToken(): String? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(REFRESH_TOKEN_KEY, null)

//    suspend fun reissueToken(): Result<ReissueTokenResponse> = withContext(Dispatchers.IO) {
//        try {
//            val refreshToken = getRefreshToken() ?: throw Exception("No refresh token available")
//            val request = ReissueTokenRequest(refreshToken)
//            val response: Response<String> = apiService.post(REISSUE_URL, request)
//
//            if (response.isSuccessful) {
//                val reissueResponse = Json.decodeFromString<ReissueTokenResponse>(response.body() ?: "")
//                saveTokens(reissueResponse.accessToken, reissueResponse.refreshToken)
//                Result.success(reissueResponse)
//            } else {
//                Result.failure(Exception("Token reissue failed: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
}