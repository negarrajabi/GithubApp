package com.example.scotiabankproject.ui.screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.scotiabankproject.R
import com.example.scotiabankproject.model.Repo
import com.example.scotiabankproject.model.User
import com.example.scotiabankproject.network.Result
import com.example.scotiabankproject.ui.component.CustomLoading
import com.example.scotiabankproject.ui.component.ErrorComponent
import com.example.scotiabankproject.ui.component.RepoItem

@Composable
fun UserProfileScreen(
    userResult: Result<User>,
    reposResult: Result<List<Repo>>,
    onRepoClick: (Repo) -> Unit,
    onSearch: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Search Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Enter GitHub Username") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onSearch(username) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display user data or error message
        when (userResult) {
            is Result.NotLoaded -> {}
            is Result.Loading -> CustomLoading()
            is Result.Success -> {
                val user = userResult.data
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberImagePainter(
                            data = user.avatar_url,
                            builder = {
                                placeholder(R.drawable.ic_git_placeholder)
                            }
                        ),
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(128.dp),
                    )
                    Text(text = user.name?: "no-name", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                }
            }
            is Result.Error -> ErrorComponent(userResult.message)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display repositories list or error message
        if(reposResult is Result.Success){
                    val repos = reposResult.data
                LazyColumn {
                    items(repos) { repo ->
                        RepoItem(repo = repo, onClick = { onRepoClick(repo) })
                    }
                }
        }
    }
}
