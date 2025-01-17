package com.example.socialconnect.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialconnect.Objects.BottomNavigationItemListData.NavigationItemsList
import com.example.socialconnect.ui.theme.focusColor

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

                        onClick = { clickIndex = index },

                        icon = { Icon(navigationData.icon, contentDescription = navigationData.title, modifier = Modifier.size(20.dp)) },

                        colors = NavigationBarItemColors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Unspecified,
                            selectedIndicatorColor = focusColor,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Unspecified,
                            disabledIconColor = Color.Unspecified,
                            disabledTextColor = Color.Unspecified
                        ),
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
fun ContentDisplay(modifier: Modifier,navigationSelectedIndex: Int) {
    when (navigationSelectedIndex) {
        0 -> HomeScreen()
        2 -> AddPostScreen()
        3 -> SettingsScreen()
        4 -> ProfileScreen()
        else -> HomeScreen()
    }
}
