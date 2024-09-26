package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getChallenges(): Unit{
        //TODO: Request, Response 클래스 만들고 함수 완성
    }

    suspend fun createChallenges(): Unit{
        //TODO: Request, Response 클래스 만들고 함수 완성
    }
}