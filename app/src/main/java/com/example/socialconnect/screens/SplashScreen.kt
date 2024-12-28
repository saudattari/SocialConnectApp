package com.example.socialconnect.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.socialconnect.R

@Preview
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(Color.Blue, Color.Cyan)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImageLoader(
                R.drawable.logo,
                modifier = Modifier
                    .size(200.dp)
            )
            Text(
                text = "Social Linkup",
                modifier = Modifier,
                color = Color.White, fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Develop by\nSaud",
                modifier = Modifier.padding(0.dp,0.dp,0.dp,6.dp),
                color = Color.White, fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun CoilImageLoader(url: Any, modifier: Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        colorFilter = ColorFilter.tint(Color.White)
    )

}