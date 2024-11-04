package com.example.scotiabankproject.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scotiabankproject.model.Repo


@Composable
fun RepoItem(
    repo: Repo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .shadow(10.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ){
        Text(
            text = repo.name,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = repo.description ?: "No description",
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Preview
@Composable
private fun RepoItemPreview(){
    RepoItem(repo = Repo("title", "description","100",100,200)) {
        
    }
}