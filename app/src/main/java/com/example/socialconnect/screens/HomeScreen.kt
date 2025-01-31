package com.example.socialconnect.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.CommentData
import com.example.socialconnect.dataModel.PostData
import com.example.socialconnect.navigation.NavigationRoute
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    LazyCol(navHostController)
                }

            }
        }

    }
}

@Composable
fun FeedItemsDesign(listOfPost: PostData, navHostController: NavHostController) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser?.uid
    var commentPost by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var commentOpen by remember { mutableStateOf(false) }
    var commentList by remember { mutableStateOf<List<CommentData>>(emptyList()) }
    val db = FirebaseFirestore.getInstance()
    var isLiked by remember { mutableStateOf(false) }
    CheckLikeDefaultState(
        db = db, postId = listOfPost.postId,
        userId = listOfPost.userId,
        onComplete = {
            isLiked = it
        }
    )
    if (commentOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LaunchedEffect(Unit) {
                    try {
                        val dbRef = db.collection("posts").document(listOfPost.postId)
                            val snapShot = dbRef.get().await()
                        if(!snapShot.exists()){
                            Toast.makeText(context, "Collection or Document Not Exist", Toast.LENGTH_SHORT).show()
                            return@LaunchedEffect
                        }
                        val dbRef2 = dbRef.collection("comments")
                            .orderBy("timeAgo", Query.Direction.DESCENDING).get().await()
                        commentList = dbRef2.documents.mapNotNull { documentSnapshot ->
                            documentSnapshot.toObject(CommentData::class.java)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
                if(commentList.isNotEmpty()){
                    LazyColumn {
                        items(commentList) {
                            CommentDesign(it)
                        }
                    }
                }
                else{
                    Toast.makeText(context, "Sorry! there is no comment yet", Toast.LENGTH_SHORT).show()
                }
                Spacer(modifier = Modifier
                    .height(20.dp)
                    .weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = commentPost,
                        placeholder = { Text(text = "Post a Comment") },
                        onValueChange = {
                            commentPost = it
                        },
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        textStyle = TextStyle(fontSize = 14.sp)
                    )
                    IconButton(onClick = {
                        addComment(
                            postId = listOfPost.postId,
                            commentData = CommentData(
                                userName = "",
                                commentContent = commentPost,
                                timeAgo = System.currentTimeMillis(),
                                userId = currentUser?:"",
                                postId =listOfPost.postId
                            ),
                            onSuccess ={
                                commentPost = ""
                            } ,
                        )
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send Icon"
                        )
                    }
                }
            }
        }
    } else {
        commentOpen = false
    }

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
                userNameExtract(db = db, onComplete = {usersName-> userName = usersName }, userId = listOfPost.userId)
                Text(text = userName, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.clickable {
                    if (listOfPost.userId == currentUser){
                        navHostController.navigate(NavigationRoute.Profile.route)
                    }
                    else{
                        navHostController.navigate("${NavigationRoute.OtherProfileScreen}/${listOfPost.userId}")
                    }
                })

                Text(
                    text = calculateTimeStamp(listOfPost.timeAgo),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz, contentDescription = "More Options"
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    Text(text = "Edit", modifier = Modifier.clickable { expanded = false })
                    Text(text = "Delete", modifier = Modifier.clickable { expanded = false })
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Post Content (Text + Image)
            Text(
                text = listOfPost.postContent,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            listOfPost.postImage?.forEach { image ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                IconButton(onClick = {
                    toggleLike(listOfPost.postId, listOfPost.userId){
                        isLiked = it
                    }
                }) {
                    Icon(imageVector = if(isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = "Like Button", tint = if (isLiked) Color.Red else Color.Black)
                }
                IconButton(onClick = {
                    commentOpen = true
                }) {
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
fun CheckLikeDefaultState(db:FirebaseFirestore,postId: String,userId: String,onComplete: (Boolean) -> Unit) {
    var isLiked by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        db.collection("posts").document(postId)
            .get()
            .addOnSuccessListener {
                val likes = it.get("likes") as? Map<String,Boolean> ?: emptyMap()
                isLiked = likes.containsKey(userId)
                onComplete(isLiked)
            }
    }
}

@Composable
fun LazyCol(navHostController: NavHostController) {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val postList = remember { mutableStateOf<List<PostData>>(emptyList()) }
    LaunchedEffect(Unit) {
        try {
            val dbs = db.collection("posts")
                .orderBy("timeAgo", Query.Direction.DESCENDING)
                .get()
                .await()
            val post = dbs.documents.mapNotNull { it.toObject(PostData::class.java) }
            postList.value = post
        } catch (e: Exception) {
            Toast.makeText(context, "Error loading posts: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    LazyColumn {
        items(postList.value) { post ->
            FeedItemsDesign(post, navHostController)
        }
    }
}

@Composable
fun CommentDesign(commentData: CommentData) {
    var userName by remember { mutableStateOf<String?>(null) }
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(userName) {
        db.collection("users").document(commentData.userId)
            .get()
            .addOnSuccessListener { document ->
                userName = if (document != null && document.exists()) {
                    document.getString("userName") ?: "Unknown"
                } else {
                    "Unknown"
                }
            }.addOnFailureListener { userName = it.message }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
//            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.LightGray),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                imageVector = Icons.Default.Person, contentDescription = "", Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = userName!!,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = commentData.commentContent,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )
            }

        }
    }
}


fun calculateTimeStamp(timeStamp: Long): String {
    val currentTime = System.currentTimeMillis()
    val timeDifference = currentTime - timeStamp
    val minutes = timeDifference / 60000
    val hour = timeDifference / 3600000
    val days = timeDifference / 86400000

    return when {
        minutes < 1 -> "just now"
        minutes < 60 -> "$minutes minutes ago"
        hour < 24 -> "$hour hours ago"
        days == 1L -> "Yesterday"
        days < 7 -> "$days days ago"
        else -> {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            sdf.format(timeStamp)
        }
    }
}

fun toggleLike(postId: String, userId: String, onComplete: (Boolean) ->Unit){
    val db = FirebaseFirestore.getInstance()
    val dbRef = db.collection("posts").document(postId)
    db.runTransaction { transaction->
        val snapshot = transaction.get(dbRef)
        val likes = snapshot.get("likes") as? MutableMap<String, Boolean> ?:mutableMapOf()
        val isLike = if(likes.containsKey(userId)){
            likes.remove(userId)
            false
        }
        else{
            likes[userId] = true
            true
        }
        transaction.update(dbRef, "likes",likes)
        isLike
    }
        .addOnSuccessListener { onComplete(it) }
        .addOnFailureListener { onComplete(false) }
}

fun addComment(postId:String ,commentData: CommentData, onSuccess:()->Unit) {
    val db = FirebaseFirestore.getInstance()
    val dbRef = db.collection("posts").document(postId)
    dbRef.get().addOnSuccessListener {
            if (it.exists()){
                dbRef.collection("comments").add(commentData)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener {  }
            }
        }.addOnFailureListener {  }

}

fun userNameExtract(db:FirebaseFirestore, onComplete: (String) -> Unit, userId: String){
    val dbRef = db.collection("users").document(userId)
    dbRef.get()
        .addOnSuccessListener { documentSnapshot->
            val userName = (documentSnapshot.get("name") ?:"Unknown").toString()
            onComplete(userName)
        }
        .addOnFailureListener { onComplete("Unknown") }
}