package com.example.socialconnect.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.SettingsData

@Preview(showBackground = true)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val itemsList = listOf(
        SettingsData(icon = Icons.Default.PersonAdd, title = "Invite Friends",onClick = { Toast.makeText(context, "Invite Friends", Toast.LENGTH_SHORT).show()}),
        SettingsData(icon = Icons.Default.Notifications, title = "Notifications",onClick = { Toast.makeText(context, "Notifications", Toast.LENGTH_SHORT).show()}),
        SettingsData(icon = Icons.Default.Save, title = "Saved Posts",onClick = { Toast.makeText(context, "Saved Posts", Toast.LENGTH_SHORT).show()}),
        SettingsData(icon = Icons.Default.Lock, title = "Privacy and Security",onClick = { Toast.makeText(context, "Privacy and security", Toast.LENGTH_SHORT).show()}),
        SettingsData(icon = Icons.Default.AccountCircle, title = "Account", onClick = { Toast.makeText(context, "Account", Toast.LENGTH_SHORT).show()}),
        SettingsData(icon = Icons.Default.Info, title = "About",onClick = { Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()}),
    )
   Scaffold {innerPadding->
       Box(modifier = Modifier
           .fillMaxSize()
           .padding(innerPadding)) {
           Column(modifier = Modifier.fillMaxWidth()) {
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   IconButton(onClick = {}) {
                       Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                   }
                   Text(
                       text = "Settings",
                       modifier = Modifier,
                       fontSize = 22.sp,
                       fontFamily = FontFamily(
                           Font(R.font.roboto_bold)
                       )
                   )
               }
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .height(1.dp)
                   .background(Color.Gray)){}
//            Settings Items
               LazyColumn {
                   items(itemsList){items->
                       SettingsItems(items)
                   }
               }
               Spacer(modifier = Modifier.height(20.dp))
               Row(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Gray)){}
               Spacer(modifier = Modifier.height(20.dp))
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .clickable { }
                   .padding(horizontal = 16.dp, vertical = 22.dp),
                   verticalAlignment = Alignment.CenterVertically) {
                   Icon(
                       imageVector = Icons.AutoMirrored.Filled.Logout,
                       contentDescription = "Log out",
                       tint = Color.Red
                   )
                   Text(
                       text = "Log out",
                       modifier = Modifier.padding(start = 12.dp),
                       fontFamily = FontFamily(Font(R.font.roboto_medium)),
                       fontSize = 18.sp,
                       color = Color.Red
                   )
               }
           }
       }
   }
}

@Composable
fun SettingsItems(settingsData: SettingsData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { settingsData.onClick }
            .padding(horizontal = 16.dp, vertical = 22.dp)
        , verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = settingsData.icon,
            contentDescription = settingsData.title,
            tint = Color.Black,
        )
        Text(
            text = settingsData.title,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 12.dp),
            fontFamily = FontFamily(Font(R.font.roboto_medium))
        )
    }
}
