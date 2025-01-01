package com.example.socialconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.socialconnect.screens.LogInScreen
import com.example.socialconnect.screens.RegistrationScreen
import com.example.socialconnect.screens.SignupScreen
import com.example.socialconnect.screens.SplashScreen


@Composable
fun ScreenNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
                SplashScreen(navController)
        }
        composable("LoginScreen") {
                LogInScreen(navController)
        }
        composable("SignupScreen") {
                SignupScreen(navController)
        }
        composable("RegistrationScreen") {
                RegistrationScreen(navController)
        }
    }
}