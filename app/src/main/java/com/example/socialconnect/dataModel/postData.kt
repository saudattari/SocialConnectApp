package com.example.socialconnect.dataModel

data class PostData(
    val userName:String,
    val timeAgo: String,
    val profileImage: String,
    val postContent: String,
    val postImage: String? = null)
