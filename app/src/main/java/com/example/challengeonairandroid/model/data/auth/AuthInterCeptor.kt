package com.example.challengeonairandroid.model.data.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val originalRequest = chain.request()
        val accessToken = tokenManager.getAccessToken()
        val request = originalRequest.newBuilder()
            .apply {
                accessToken?.let {
                    header("Authorization", "Bearer $it")
                }
            }
            .build()

        val response = chain.proceed(request)

        if (response.code == 401 && response.body?.string()?.contains("access token expired") == true) {
            response.close()
            return@runBlocking handleTokenRefresh(chain, originalRequest)
        }

        response
    }

    private suspend fun handleTokenRefresh(chain: Interceptor.Chain, originalRequest: Request): Response {
        val refreshToken = tokenManager.getRefreshToken() ?:
        throw IllegalStateException("Refresh token not found")

        val refreshRequest = Request.Builder()
            .url("${chain.request().url.newBuilder().host("api.example.com").build()}/reissue")
            .post(RequestBody.create(null, ByteArray(0)))
            .addHeader("Authorization", "Bearer $refreshToken")
            .build()

        val refreshResponse = chain.proceed(refreshRequest)

        return if (refreshResponse.isSuccessful) {
            refreshResponse.body?.string()?.let { responseBody ->
                // Parse the response and extract new tokens
                // This is a simplified example. You should use proper JSON parsing here.
                val newAccessToken = responseBody.substringAfter("\"access_token\":\"").substringBefore("\"")
                val newRefreshToken = responseBody.substringAfter("\"refresh_token\":\"").substringBefore("\"")

                // Save new tokens
                tokenManager.saveAccessToken(newAccessToken)
                tokenManager.saveRefreshToken(newRefreshToken)

                // Retry the original request with the new access token
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()

                refreshResponse.close()
                chain.proceed(newRequest)
            } ?: refreshResponse
        } else {
            refreshResponse
        }
    }
}