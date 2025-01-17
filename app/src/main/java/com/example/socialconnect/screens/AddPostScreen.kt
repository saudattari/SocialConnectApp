package com.example.socialconnect.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AddPostScreen() {
    Toast.makeText(LocalContext.current, "Add Post Screen", Toast.LENGTH_SHORT).show()
}