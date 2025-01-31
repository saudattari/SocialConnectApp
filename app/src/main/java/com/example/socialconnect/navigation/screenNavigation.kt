package com.example.socialconnect.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.socialconnect.screens.AddPostScreen
import com.example.socialconnect.screens.HomeScreen
import com.example.socialconnect.screens.LogInScreen
import com.example.socialconnect.screens.MainScreen
import com.example.socialconnect.screens.OtherProfileScreen
import com.example.socialconnect.screens.ProfileScreen
import com.example.socialconnect.screens.RegistrationScreen
import com.example.socialconnect.screens.SettingsScreen
import com.example.socialconnect.screens.SignupScreen
import com.example.socialconnect.screens.SplashScreen


@Composable
fun ScreenNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoute.SplashScreen.route) {
        composable(NavigationRoute.SplashScreen.route) {
                SplashScreen(navController)
        }
        composable(NavigationRoute.LoginScreen.route) {
                LogInScreen(navController)
        }
        composable(NavigationRoute.SignupScreen.route) {
                SignupScreen(navController)
        }
        composable(NavigationRoute.RegistrationScreen.route) {
                RegistrationScreen(navController)
        }
        composable(NavigationRoute.MainScreen.route) {
                MainScreen(navController)
        }
        composable(NavigationRoute.Profile.route) {
                ProfileScreen()
        }
        composable(NavigationRoute.OtherProfileScreen.route) {
            val userId = it.arguments?.getString("userId") ?:""
            OtherProfileScreen(userId)
        }

    }
}