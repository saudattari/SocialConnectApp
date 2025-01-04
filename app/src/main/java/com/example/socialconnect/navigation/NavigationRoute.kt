package com.example.socialconnect.navigation

sealed class NavigationRoute(val route: String) {
    data object SplashScreen : NavigationRoute(route = "SplashScreen")
    data object SignupScreen: NavigationRoute(route ="SignupScreen")
    data object LoginScreen: NavigationRoute(route ="LoginScreen")
    data object RegistrationScreen : NavigationRoute(route ="RegistrationScreen")
}