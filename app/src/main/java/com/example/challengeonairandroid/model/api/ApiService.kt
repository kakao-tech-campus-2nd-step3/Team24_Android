package com.example.challengeonairandroid.model.api

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET
    suspend fun get(@Url url: String): Response<String>

    @GET
    suspend fun get(@Url url: String, @QueryMap queryMap: Map<String, String>): Response<String>

    @POST
    suspend fun post(@Url url: String): Response<String>

    @POST
    suspend fun post(@Url url: String, @Body body: Any): Response<String>

    @PUT
    suspend fun put(@Url url: String): Response<String>

    @PUT
    suspend fun put(@Url url: String, @Body body: Any): Response<String>

    @DELETE
    suspend fun delete(@Url url: String): Response<String>

    @HTTP(method = "DELETE", hasBody = true)
    suspend fun deleteWithBody(@Url url: String, @Body body: Any): Response<String>

    @PATCH
    suspend fun patch(@Url url: String, @Body body: Any): Response<String>

    @HEAD
    suspend fun head(@Url url: String): Response<Void>
}