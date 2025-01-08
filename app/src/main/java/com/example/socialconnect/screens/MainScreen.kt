package com.example.socialconnect.screens

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.socialconnect.Objects.BottomNavigationItemListData.NavigationItemsList
import com.example.socialconnect.dataModel.NavigationData
import okhttp3.internal.http2.Settings

@Preview
@Composable
fun MainScreen() {
    var clickIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationItemsList.forEachIndexed { index, navigationData ->
                    NavigationBarItem(
                        selected = index == clickIndex,
                        onClick = {
                            clickIndex = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = navigationData.badgeCount.toString())
                                }
                            }) { navigationData.icon }
                        },
                        label = { navigationData.title }
                    )

                }
            }
        }

    ) { innerPadding ->
        ContentDisplay(
            modifier = Modifier.padding(innerPadding),
            navigationSelectedIndex = clickIndex
        )
    }
}

@Composable
fun ContentDisplay(modifier: Modifier, navigationSelectedIndex: Int) {
    when (navigationSelectedIndex) {
        0 -> HomeScreen(modifier)
        1 -> AddPostScreen(modifier)
        2 -> SettingsScreen(modifier)
        3 -> ProfileScreen(modifier)
        else -> HomeScreen(modifier)
    }
}
