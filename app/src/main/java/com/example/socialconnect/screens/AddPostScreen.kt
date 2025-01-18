package com.example.socialconnect.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialconnect.R
import com.example.socialconnect.ui.theme.clickColor

@Preview
@Composable
fun AddPostScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {},Modifier.clip(RoundedCornerShape(50.dp)), containerColor = clickColor, contentColor = Color.White) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Post")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .clickable { }
                            .size(30.dp)
                    )
                    Text(
                        text = "Add Post",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black
                    )
                }
//                horizontal Line
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)) { }
//                Col for adding data
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.photo),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100.dp))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "mohammadsaud_attari", fontWeight = FontWeight.Bold, fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        placeholder = { Text(text = "What's New") },
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        maxLines = 10,
                    )
//                    add Action buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().padding(vertical = 3.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                      IconButton(onClick = {}, modifier = Modifier,) { Icon(imageVector = Icons.Default.AddPhotoAlternate, contentDescription = "Add Image", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Open Camera", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.Tag, contentDescription = "Add Hashtag", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Add Location", tint = Color.Black) }
                  }
                }
            }
        }

    }
}