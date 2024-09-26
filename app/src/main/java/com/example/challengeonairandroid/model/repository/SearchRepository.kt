package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun search(): Unit{
        //TODO: Request, Response 클래스 만들고 함수 완성
    }
}