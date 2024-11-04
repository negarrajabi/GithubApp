package com.example.scotiabankproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scotiabankproject.model.Repo
import com.example.scotiabankproject.network.Result
import com.example.scotiabankproject.ui.screen.RepoDetailScreen
import com.example.scotiabankproject.ui.screen.UserProfileScreen
import com.example.scotiabankproject.viewmodel.RepoDetailsViewModel
import com.example.scotiabankproject.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModel()
    private val repoDetailsViewModel: RepoDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    val userResult by userViewModel.userData.collectAsState()
                    val reposResult by userViewModel.reposData.collectAsState()

                    NavHost(navController = navController, startDestination = "userProfile") {
                        composable("userProfile") {
                            UserProfileScreen(
                                userResult = userResult,
                                reposResult = reposResult,
                                onRepoClick = { repo ->
                                    val totalForks = if (reposResult is Result.Success) {
                                        (reposResult as Result.Success<List<Repo>>).data.sumOf { it.forks }
                                    } else {
                                        0
                                    }
                                    repoDetailsViewModel.setRepoDetail(repo, totalForks)
                                    navController.navigate("repoDetail")
                                },
                                onSearch = { username ->
                                    userViewModel.fetchUserData(username)
                                }
                            )
                        }
                        composable("repoDetail") {
                            RepoDetailScreen(viewModel = repoDetailsViewModel,
                                onBackPressed = {
                                    navController.popBackStack()
                                })
                        }
                    }
                }
            }
        }
    }
}