package com.example.socialconnect.Objects

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.example.socialconnect.dataModel.NavigationData

object BottomNavigationItemListData {
    val NavigationItemsList = listOf(
        NavigationData(icon = Icons.Default.Home, title = "Home", badgeCount = 0),
        NavigationData(icon = Icons.Default.AddBox, title = "Add", badgeCount = 1),
        NavigationData(icon = Icons.Default.Settings, title = "Settings", badgeCount = 2),
        NavigationData(icon = Icons.Default.Person, title = "Profile", badgeCount = 3)
    )
}