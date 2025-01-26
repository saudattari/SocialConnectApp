package com.example.socialconnect.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.PostData
import com.example.socialconnect.dataModel.ProfileData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await


@Preview
@Composable
fun ProfileScreen() {
    var profileImageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    val userID = FirebaseAuth.getInstance().currentUser!!.uid

    val imagePickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            uploadImageToCloudinary(context = context, fileUri = it,
                onSuccess = { imageUrl ->
                    profileImageUrl = imageUrl
                   if(imageUrl.isNotEmpty()){
                       uploadImageToFireStore(
                           imageURl = imageUrl,
                           onSuccess = { Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show() },
                           onError = { Toast.makeText(context, "Image not uploaded", Toast.LENGTH_SHORT).show()},
                           userId = userID
                       )
                   }
                },
                onError = { error ->
                    Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
    LaunchedEffect(userID) {
        fetchFromFirestore(userID, onSuccess = {
            profileImageUrl = it.profilePicture
        })
    }

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
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }

                // Profile Details and Image Upload Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier) {
                        Text(
                            text = "Mohammad Saud",
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold))
                        )
                        Text(
                            text = "mohammadsaud_attari",
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            modifier = Modifier.padding(top = 7.dp)
                        )
                        Text(
                            text = "100 followers",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .clickable {
                                imagePickerLauncher.launch("image/*") // Open image picker
                            }
                        , contentAlignment = Alignment.Center
                    ) {
                        if (profileImageUrl.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(profileImageUrl),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.PersonAddAlt1,
                                contentDescription = "Add Profile Image",
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                // Posts Section
                PostLazyCol(db = db, currentUser = userID)
            }
        }
    }
}




@Composable
fun PostItemsDesign(listOfPost: PostData) {
    var expanded by remember { mutableStateOf(false) }
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
                    painter = rememberAsyncImagePainter(R.drawable.photo),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "", fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
                Text(
                    text = "5h",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz, contentDescription = "More Options"
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        Text(text = "Edit", modifier = Modifier.clickable { expanded = false })
                        Text(text = "Delete", modifier = Modifier.clickable {  expanded = false})
                    }
                }
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
fun PostLazyCol(db: FirebaseFirestore, currentUser:String) {
    var listOfPost by remember { mutableStateOf<List<PostData>>(emptyList()) }
    LaunchedEffect(Unit) {
       val dbRef =  db.collection("posts")
            .whereEqualTo("userId", currentUser)
            .get()
            .await()
        val post = dbRef.documents.mapNotNull {
            it.toObject(PostData::class.java)
        }
        listOfPost = post
    }
    if (listOfPost.isEmpty()) {
        Text(
            text = "No posts to display",
            modifier = Modifier, textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Gray
        )
    } else {
        LazyColumn {
            items(listOfPost) {
                PostItemsDesign(it)
            }
        }
    }

}


fun uploadImageToCloudinary(context: Context, fileUri: Uri, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
    MediaManager.get().upload(fileUri)
        .callback(object : UploadCallback {
            override fun onStart(requestId: String?) {
                Toast.makeText(context, "Upload Started....", Toast.LENGTH_SHORT).show()
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                val progress = (bytes.toDouble() / totalBytes) * 100
                Toast.makeText(context, "Upload Progress ${progress.toInt()}%", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                val imageUrl = resultData?.get("url") as String
                onSuccess(imageUrl)
                Toast.makeText(context, "Upload Success", Toast.LENGTH_SHORT).show()
            }

            override fun onError(requestId: String?, error: ErrorInfo?) {
                Toast.makeText(context, "Upload Error", Toast.LENGTH_SHORT).show()
                onError(error.toString())
            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {}

        }).dispatch()

}

fun uploadImageToFireStore(userId: String,imageURl: String, onSuccess: () -> Unit,onError: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userDoc = db.collection("users").document(userId)

    val data = ProfileData(profilePicture = imageURl)
    userDoc.set(data, SetOptions.merge())
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { onError() }
}


fun fetchFromFirestore(userID: String, onSuccess: (ProfileData) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userDoc = db.collection("users").document(userID)
    userDoc.get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val profileData = documentSnapshot.toObject(ProfileData::class.java)
                profileData?.let {
                    onSuccess(it)
                }
            }

        }
}
