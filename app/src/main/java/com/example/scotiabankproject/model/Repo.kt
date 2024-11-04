package com.example.scotiabankproject.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val name: String,
    val description: String?,
    val updated_at: String,
    val stargazers_count: Int,
    val forks: Int,
)