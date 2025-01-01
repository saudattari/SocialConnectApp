package com.example.socialconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.socialconnect.navigation.ScreenNavigation
import com.example.socialconnect.ui.theme.SocialConnectTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            SocialConnectTheme {
                val navHostController = rememberNavController()
                ScreenNavigation(navHostController)
            }
        }
    }
}