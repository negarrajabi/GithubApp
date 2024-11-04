package com.example.scotiabankproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scotiabankproject.R
import com.example.scotiabankproject.ui.component.CustomToolbar
import com.example.scotiabankproject.ui.component.RepoDetailRow
import com.example.scotiabankproject.viewmodel.RepoDetailsViewModel
@Composable
fun RepoDetailScreen(
    viewModel: RepoDetailsViewModel,
    onBackPressed: () -> Unit
) {
    val repo by viewModel.repoDetail.collectAsState()
    val showBadge by viewModel.showForkBadge.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),

    ) {
        CustomToolbar(title =repo?.name?: "") {
            onBackPressed()
        }

        Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            repo?.let {
                if (showBadge) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_golden_user),
                        contentDescription = "Badge Icon",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(80.dp)

                    )
                    Text(text = "GitHubber!", color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = it.description ?: "No description available")
                RepoDetailRow(
                    icon = R.drawable.ic_fork,
                    title = "Forks",
                    amount = it.forks.toString()
                )
                RepoDetailRow(
                    icon = R.drawable.ic_star,
                    title = "Stars",
                    amount = it.stargazers_count.toString()
                )
            }
        }
    }
}
