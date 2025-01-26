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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Locale

@Preview
@Composable
fun HomeScreen() {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser?.uid
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

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)) {
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
                    painter = rememberAsyncImagePainter(R.drawable.logo),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = listOfPost.userId, fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
                Text(
                    text = calculateTimeStamp(listOfPost.timeAgo),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz, contentDescription = "More Options"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Post Content (Text + Image)
            Text(
                text = listOfPost.postContent,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            for (image in listOfPost.postImage!!){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(image),
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
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
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    var postList by remember { mutableStateOf<List<PostData>>(emptyList())  }
   LaunchedEffect(Unit) {
       try{
           val dbs = db.collection("posts")
               .orderBy("timeAgp", Query.Direction.DESCENDING)
               .get()
               .await()
           val post = dbs.documents.mapNotNull { it.toObject(PostData::class.java) }
           postList = post
       }catch (e:Exception){
           Toast.makeText(context, "Error loading posts: ${e.message}", Toast.LENGTH_SHORT).show()
       }
   }
    LazyColumn {
        items(postList){post->
            FeedItemsDesign(post)
        }
    }
}

fun calculateTimeStamp(timeStamp:Long):String{
    val currentTime = System.currentTimeMillis()
    val timeDifference = currentTime - timeStamp
    val minutes = timeDifference / 60000
    val hour = timeDifference / 3600000
    val days = timeDifference / 86400000

    return when{
        minutes < 1 -> "just now"
        minutes < 60 -> "$minutes minutes ago"
        hour < 24 -> "$hour hours ago"
        days == 1L -> "Yesterday"
        days<7 -> "$days days ago"
        else->{
         val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            sdf.format(timeStamp)
        }
    }
}