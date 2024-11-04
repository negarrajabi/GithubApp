package com.example.scotiabankproject.ui.component

import android.annotation.SuppressLint
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CustomToolbar(
    title: String,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(

            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable(
                    onClick = onBackPressed,
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(
                        bounded = false, // Make ripple circular
                        radius = 24.dp,
                        color = MaterialTheme.colorScheme.primary // Optional ripple color
                    )
                )
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Preview
@Composable
private fun ToolbarPreview(){
    CustomToolbar(title = "title") {
    }
}

