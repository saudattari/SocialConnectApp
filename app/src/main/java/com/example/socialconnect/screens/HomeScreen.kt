package com.example.socialconnect.screens

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.PostData

@Preview
@Composable
fun HomeScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
//                Main logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                )
//                Spacer to add space
                Spacer(modifier = Modifier.height(10.dp))

//                Feed Profile photo + Add Post shortcut
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.photo),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100.dp))
                            .size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(text = "Write Something") },
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clickable {

                            },
                        textStyle = TextStyle(fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(),
                        readOnly = true,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                ) { }

                Column(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
                    LazyCol()
                }

            }
        }

    }
}

@Composable
fun FeedItemsDesign(listOfPost: PostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.White),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(listOfPost.profileImage),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = listOfPost.userName, fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
                Text(
                    text = listOfPost.timeAgo,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreHoriz, contentDescription = "More Options"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Post Content (Text + Image)
            Text(
                text = listOfPost.postContent,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            listOfPost.postImage?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Post Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            ) { }
//            Action button like, comment
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like")
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comment"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            }
        }
    }
}

@Composable
fun LazyCol() {
    val postList = listOf(
        PostData(
            userName = "mohammadsaud_attari",
            timeAgo = "3h",
            profileImage = R.drawable.photo,
            postContent = "I'm Saud and I am an Android Developer. This is my new App Logo",
            postImage = R.drawable.logo
        ), PostData(
            userName = "hafiz_farhan",
            timeAgo = "5h",
            profileImage = R.drawable.farhan,
            postContent = "Excited to share my new blog post! Excited to share my new blog post! Excited to share my new blog post!",
        ), PostData(
            userName = "hafiz_farhan",
            timeAgo = "5h",
            profileImage = R.drawable.farhan,
            postContent = "Excited to share my new blog post! Excited to share my new blog post! Excited to share my new blog post!",
        )
    )
    LazyColumn {
        items(postList) { post ->
            FeedItemsDesign(post)
        }
    }
}