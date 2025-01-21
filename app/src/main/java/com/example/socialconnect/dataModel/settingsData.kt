package com.example.socialconnect.dataModel

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsData(
    val icon : ImageVector,
    val title : String,
    val onClick : () -> Unit,
)