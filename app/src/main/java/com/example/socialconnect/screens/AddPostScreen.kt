package com.example.socialconnect.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.socialconnect.R
import com.example.socialconnect.dataModel.PostData
import com.example.socialconnect.ui.theme.clickColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

@Preview
@Composable
fun AddPostScreen() {
    var postText by remember { mutableStateOf("") }
    val listImages = remember { mutableStateListOf<String>() }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser?.uid
    var isLoading by remember { mutableStateOf(false) }

//    LoadingScreen
    if(isLoading){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.White)
        }
    }

    val imageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                isLoading = true
                uploadImageToCloudinar(
                    fileUri = it,
                    onSuccess = {fileUri->
                        listImages.add(fileUri)
                        isLoading = false
                    },
                    context = context,
                    onError = {
                        isLoading = false
                    }
                )
            }
        }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    currentUser?.let {
                        if(postText.isNotEmpty() || listImages.isNotEmpty()){
                            val postId =db.collection("posts").document().id
                            val post = PostData(
                                postId =postId,
                                userId = it,
                                timeAgo = System.currentTimeMillis(),
                                postContent = postText,
                                postImage = listImages.ifEmpty { null },
                            )
                            addPost(
                                post = post,
                                onSuccess = { Toast.makeText(context, "Post added", Toast.LENGTH_SHORT).show()
                                    postText = "" // Reset text field
                                    listImages.clear() // Clear images
                                     },
                                context = context
                            )
                        }
                        else{
                            Toast.makeText(context, "Add text or Image", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                Modifier.clip(RoundedCornerShape(50.dp)),
                containerColor = clickColor,
                contentColor = Color.White,
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Post")
            }
        }

    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                ) { }
//                Col for adding data
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
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
                            text = "mohammadsaud_attari",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = postText,
                        placeholder = { Text(text = "What's New") },
                        onValueChange = { postText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        maxLines = 10,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (listImages.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            items(listImages) { url ->
                                Image(
                                    painter = rememberAsyncImagePainter(url),
                                    contentDescription = "Uploaded Image",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(end = 8.dp)
                                )
                            }
                        }
                    }


//                    add Action buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                imageLauncher.launch("image/*")
                            },
                            modifier = Modifier,
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddPhotoAlternate,
                                contentDescription = "Add Image",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Open Camera",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Tag,
                                contentDescription = "Add Hashtag",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Add Location",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

fun uploadImageToCloudinar(fileUri: Uri, onSuccess: (String) -> Unit, context: Context,onError: () -> Unit) {
    MediaManager.get().upload(fileUri).option("folder", "Social Connect").callback(object : UploadCallback {
        override fun onStart(requestId: String?) {
            Toast.makeText(context, "Upload started", Toast.LENGTH_SHORT).show()

        }

        override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
            Toast.makeText(context, "In Progress", Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
            val fileUrl = resultData?.get("url") as String
            Toast.makeText(context, "Uploading completed", Toast.LENGTH_SHORT).show()
            onSuccess(fileUrl)
        }

        override fun onError(requestId: String?, error: ErrorInfo?) {
            onError()
        }

        override fun onReschedule(requestId: String?, error: ErrorInfo?) {
        }

    })
}

fun addPost(post:PostData, onSuccess: () -> Unit,context: Context){
    val db = FirebaseFirestore.getInstance()
    val postDocRef = db.collection("posts").document(post.postId)
    postDocRef.set(post).addOnSuccessListener { onSuccess() }
        .addOnFailureListener { Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show() }
}