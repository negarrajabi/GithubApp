package com.example.scotiabankproject.repository

import com.example.scotiabankproject.model.User
import com.example.scotiabankproject.network.GithubApi
import com.example.scotiabankproject.network.safeApiCall
import com.example.scotiabankproject.network.Result


class UserRepository(private val api: GithubApi) {

    suspend fun getUser(userId: String): Result<User> {
       return  safeApiCall { api.getUser(userId) }
    }

    suspend fun getUserRepos(userId: String) =
        safeApiCall {  api.getUserRepos(userId)}

}
