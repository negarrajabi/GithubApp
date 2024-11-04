package com.example.scotiabankproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scotiabankproject.model.Repo
import com.example.scotiabankproject.model.User
import com.example.scotiabankproject.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.scotiabankproject.network.Result

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userData = MutableStateFlow<Result<User>>(Result.NotLoaded)
    val userData: StateFlow<Result<User>> = _userData

    private val _reposData = MutableStateFlow<Result<List<Repo>>>(Result.NotLoaded)
    val reposData: StateFlow<Result<List<Repo>>> = _reposData

    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            _userData.value = Result.Loading // Set loading state
            _reposData.value = Result.Loading // Set loading state for repos
            _userData.value = repository.getUser(userId) // Fetch user and update state
            _reposData.value = repository.getUserRepos(userId) // Fetch repos and update state
        }
    }
}
