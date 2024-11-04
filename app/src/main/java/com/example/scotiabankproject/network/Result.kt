package com.example.scotiabankproject.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val cause: Exception? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object NotLoaded: Result<Nothing>()
}
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error("Response body is null")
                }
            } else {

                Result.Error(response.message())
            }
        } catch (e: IOException) {
            Result.Error("Network error. Please check your connection.", e)
        } catch (e: HttpException) {
            Result.Error("HTTP error: ${e.message()}", e)
        } catch (e: Exception) {
            Result.Error("Unexpected error: ${e.localizedMessage}", e)
        }
    }
}