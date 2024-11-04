package com.example.scotiabankproject.network

import com.example.scotiabankproject.model.Repo
import com.example.scotiabankproject.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<User>

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): Response<List<Repo>>
}