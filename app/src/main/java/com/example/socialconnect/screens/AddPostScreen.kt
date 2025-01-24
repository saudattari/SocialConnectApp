package com.example.socialconnect.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.socialconnect.R
import com.example.socialconnect.ui.theme.clickColor
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import okhttp3.internal.format
import kotlin.contracts.contract

@Preview
@Composable
fun AddPostScreen() {
    var postText by remember { mutableStateOf("") }
    val listImages = mutableListOf(R.drawable.photo)
    val context  = LocalContext.current
    var imageUrlState by remember { mutableStateOf("") }
    val imageLauncher = rememberLauncherForActivityResult(contract  = ActivityResultContracts.GetContent()) {uri: Uri?->
        uri?.let {
//            upload image to Cloudinary
            uploadImageToCloudinary(
                context =context,
                fileUri = it,
                onSuccess = {fileUri->
                    imageUrlState = fileUri
                }
            )
        }
    }
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
                        value = postText,
                        placeholder = { Text(text = "What's New") },
                        onValueChange = {postText = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        maxLines = 10,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if(listImages.isNotEmpty()){
                        ForEach()
                        listImages.forEach{ imageUrl->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)) {
                                Image(painter = , contentDescription = "PotContentPicture")
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
                      IconButton(onClick = {
                          imageLauncher.launch("image/* , video/*")
                      }, modifier = Modifier,) { Icon(imageVector = Icons.Default.AddPhotoAlternate, contentDescription = "Add Image", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Open Camera", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.Tag, contentDescription = "Add Hashtag", tint = Color.Black) }
                      IconButton(onClick = {}) { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Add Location", tint = Color.Black) }
                  }
                }
            }
        }
    }
}
fun uploadImageToCloudinary(fileUri: Uri, onSuccess:(String) ->Unit, context:Context){
    MediaManager.get().upload(fileUri).callback(object :  UploadCallback{
        override fun onStart(requestId: String?) {
            Toast.makeText(context, "Upload started", Toast.LENGTH_SHORT).show()

        }

        override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
            val totalTime = (bytes.toDouble() / totalBytes) * 100
            Toast.makeText(context, "Uploading started...${totalTime.toInt()}", Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
            val fileUri =resultData?.get("url") as String
            Toast.makeText(context, "Uploading completed", Toast.LENGTH_SHORT).show()
            onSuccess(fileUri)
        }

        override fun onError(requestId: String?, error: ErrorInfo?) {
            Toast.makeText(context, "Uploading problem", Toast.LENGTH_SHORT).show()
        }

        override fun onReschedule(requestId: String?, error: ErrorInfo?) {
        }

    })
}
 fun uploadContentToFireStore(context: Context,currentUser:String,content:String, imageUrl:String,onSuccess: () -> Unit){
     val db = FirebaseFirestore.getInstance()
     val postDocRef = db.collection("posts").document(currentUser)
     val data  = hashMapOf(
         "content" to content,
         "ContentImage" to imageUrl,
         "userId" to currentUser
     )
     postDocRef.set(data, SetOptions.merge()).addOnSuccessListener {
         onSuccess()
     }
         .addOnFailureListener {
             Toast.makeText(context, "Sorry! getting error while uploading post on FireStore", Toast.LENGTH_SHORT).show()
         }
 }