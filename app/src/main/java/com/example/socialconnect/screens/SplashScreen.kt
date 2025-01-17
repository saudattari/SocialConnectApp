package com.example.socialconnect.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.socialconnect.CoilImage
import com.example.socialconnect.R
import com.example.socialconnect.navigation.NavigationRoute
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

//@Preview
@Composable
fun SplashScreen(navController: NavHostController) {
    var auth = FirebaseAuth.getInstance()
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
            CoilImage.CoilImageLoader(
                R.drawable.logo,
                modifier = Modifier
                    .size(200.dp),
                colorFilter = ColorFilter.tint(Color.White)
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
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 28.dp),
                color = Color.White, fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


        LaunchedEffect(Unit) {
            delay(2000)
            if (auth.currentUser != null) {
                navController.navigate(NavigationRoute.MainScreen.route) {
                    popUpTo(NavigationRoute.SplashScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(NavigationRoute.RegistrationScreen.route) {
                    popUpTo(NavigationRoute.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}