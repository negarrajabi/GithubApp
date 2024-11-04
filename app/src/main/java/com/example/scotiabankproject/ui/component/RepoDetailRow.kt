package com.example.scotiabankproject.ui.component

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scotiabankproject.R
import com.example.scotiabankproject.model.Repo

@Composable
fun RepoDetailRow  (
    @DrawableRes icon: Int,
    title: String,
    amount: String,
    modifier: Modifier = Modifier

) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .shadow(10.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = amount,
        )
    }
}


@Preview
@Composable
private fun RepoDetailsRowPreview(){
    RepoDetailRow(icon = R.drawable.ic_fork, title = "Forks", amount = "1000" )
}