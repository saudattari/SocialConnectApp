package com.example.socialconnect.Objects

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.example.socialconnect.dataModel.NavigationData

object BottomNavigationItemListData {
    val NavigationItemsList = listOf(
        NavigationData(icon = Icons.Default.Home, title = "Home"),
        NavigationData(icon = Icons.Default.Notifications, title = "Notification"),
        NavigationData(icon = Icons.Default.AddBox, title = "Add"),
        NavigationData(icon = Icons.Default.Settings, title = "Settings"),
        NavigationData(icon = Icons.Default.Person, title = "Profile")
    )
}