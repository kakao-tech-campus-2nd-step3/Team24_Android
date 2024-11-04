package com.example.challengeonairandroid.model.data.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val originalRequest = chain.request()
        val requestPath = originalRequest.url.encodedPath

        // login, oauth2, reissue 경로는 AccessToken 불필요
        val request = if (!requestPath.startsWith("/login") &&
            !requestPath.startsWith("/oauth2") &&
            !requestPath.startsWith("/reissue")) {
            // Access Token이 있다면 헤더에 추가
            val accessToken = tokenManager.getAccessToken()
            originalRequest.newBuilder()
                .apply {
                    accessToken?.let {
                        header("Authorization", "Bearer $it")
                    }
                }
                .build()
        } else {
            originalRequest
        }

        val response = chain.proceed(request)

        // 토큰 만료시 처리
        if (response.code == 401) {
            response.close()
            return@runBlocking handleTokenRefresh(chain, originalRequest)
        }

        // 쿠키에서 토큰 추출
        response.headers("Set-Cookie").forEach { cookie ->
            when {
                cookie.contains(ACCESS_TOKEN) -> {
                    val token = cookie.substringAfter("$ACCESS_TOKEN=")
                        .substringBefore(";")
                    tokenManager.saveAccessToken(token)
                }
                cookie.contains(REFRESH_TOKEN) -> {
                    val token = cookie.substringAfter("$REFRESH_TOKEN=")
                        .substringBefore(";")
                    tokenManager.saveRefreshToken(token)
                }
            }
        }

        response
    }

    private suspend fun handleTokenRefresh(chain: Interceptor.Chain, originalRequest: Request): Response {
        val refreshToken = tokenManager.getRefreshToken() ?:
        throw IllegalStateException("Refresh token not found")

        // 토큰 재발급 요청
        val refreshRequest = Request.Builder()
            .url("${chain.request().url.scheme}://${chain.request().url.host}/reissue")
            .post(RequestBody.create(null, ByteArray(0)))
            .build()  // RefreshToken은 쿠키에 있으므로 헤더에 추가하지 않음

        val refreshResponse = chain.proceed(refreshRequest)

        return if (refreshResponse.isSuccessful) {
            // 새로운 토큰으로 원래 요청 재시도
            val newAccessToken = tokenManager.getAccessToken()

            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()

            refreshResponse.close()
            chain.proceed(newRequest)
        } else {
            refreshResponse
        }
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}