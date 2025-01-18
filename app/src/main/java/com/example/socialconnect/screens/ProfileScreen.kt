package com.example.socialconnect.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.PostData
import kotlinx.coroutines.launch

@Preview
@Composable
fun ProfileScreen() {
    val isDrawerOpen by remember {  mutableStateOf(false)}
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(drawerContent = { }, drawerState = drawerState) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Profile Screen",
                            modifier = Modifier,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.clickable {
                                scope.launch {
//                                    isDrawerOpen = true
                                }
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                    ) { }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp, 0.dp, 12.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier) {
                            Text(
                                text = "Mohammad Saud",
                                modifier = Modifier,
                                fontSize = 25.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_bold))
                            )
                            Text(
                                text = "mohammadsaud_attari",
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                modifier = Modifier.padding(0.dp, 7.dp, 0.dp, 0.dp)
                            )
                            Text(
                                text = "100 follower",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                                color = Color.Gray
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.PersonAddAlt1,
                            contentDescription = "Add profile Image",
                            modifier = Modifier
                                .size(54.dp)
                                .background(shape = CircleShape, color = Color.LightGray)
                                .padding(12.dp)
                                .clickable {
                                },

                            )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(120.dp),
                            colors = ButtonDefaults.outlinedButtonColors(),
                            border = BorderStroke(
                                width = 1.dp, brush = Brush.linearGradient(
                                    listOf(Color.Gray, Color.Gray)
                                )
                            )
                        ) {
                            Text(text = "Edit Profile", color = Color.Black)
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(120.dp),
                            colors = ButtonDefaults.outlinedButtonColors(),
                            border = BorderStroke(
                                width = 1.dp,
                                brush = Brush.linearGradient(listOf(Color.Gray, Color.Gray))
                            )
                        ) {
                            Text(
                                text = "Log Out", color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
//                horizontal Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                            .padding(horizontal = 12.dp)
                    ) {}
                    Text(
                        text = "Posts",
                        modifier = Modifier
                            .padding(12.dp, 10.dp, 0.dp, 0.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        textDecoration = TextDecoration.Underline
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        PostsLazyCol()
                    }
                }
            }
        }
    }
}

@Composable
fun PostItemsDesign(listOfPost: PostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.White),
        colors =CardDefaults.cardColors(containerColor = Color.White),
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
                IconButton(onClick = {}){ Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "More Options") }
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
            Row(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray)) {  }
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
fun PostsLazyCol() {
    val postList = listOf(
        PostData(
            userName = "mohammadsaud_attari",
            timeAgo = "3h",
            profileImage = R.drawable.photo,
            postContent = "I'm Saud and I am an Android Developer.",
            postImage = R.drawable.photo
        ), PostData(
            userName = "hafiz_farhan",
            timeAgo = "5h",
            profileImage = R.drawable.farhan,
            postContent = "Excited to share my new blog post!",
        )
    )
    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        items(postList) { post ->
            PostItemsDesign(post)
        }
    }
}





