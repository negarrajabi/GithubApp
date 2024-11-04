package com.example.scotiabankproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scotiabankproject.model.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RepoDetailsViewModel : ViewModel() {
    private val _repoDetail = MutableStateFlow<Repo?>(null)
    val repoDetail: StateFlow<Repo?> = _repoDetail

    private val _showForkBadge = MutableStateFlow(false)
    val showForkBadge: StateFlow<Boolean> = _showForkBadge

    fun setRepoDetail(repo: Repo, totalForks: Int) {
        _repoDetail.value = repo
        _showForkBadge.value = totalForks > 5000
    }
}