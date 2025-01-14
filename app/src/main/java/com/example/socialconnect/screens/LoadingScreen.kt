package com.example.socialconnect.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview(showBackground = true
)
@Composable
fun LoadingScreen() {

    val isLoading = remember { mutableStateOf(false) }
    if (isLoading.value) {
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center)
                .size(50.dp),
                color = ProgressIndicatorDefaults.circularColor,
                strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth
            )
        }
    }

}