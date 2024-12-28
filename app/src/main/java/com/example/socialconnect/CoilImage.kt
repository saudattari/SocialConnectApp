package com.example.socialconnect

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import coil3.compose.AsyncImage

object CoilImage {
    @Composable
    fun CoilImageLoader(url: Any, modifier: Modifier,colorFilter: ColorFilter?=null) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = modifier,
            colorFilter = colorFilter
        )

    }
}