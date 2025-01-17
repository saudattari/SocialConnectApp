package com.example.socialconnect.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen() {
    Toast.makeText(LocalContext.current, "Home Screen", Toast.LENGTH_SHORT).show()
}