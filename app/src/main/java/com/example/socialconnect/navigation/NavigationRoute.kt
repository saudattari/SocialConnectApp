package com.example.socialconnect.navigation

sealed class NavigationRoute(val route: String) {
    data object SplashScreen : NavigationRoute("SplashScreen")
    data object SignupScreen: NavigationRoute("SignupScreen")
    data object LoginScreen: NavigationRoute("LoginScreen")
    data object RegistrationScreen : NavigationRoute("RegistrationScreen")
}