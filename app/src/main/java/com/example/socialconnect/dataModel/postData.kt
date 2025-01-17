package com.example.socialconnect.dataModel

data class PostData(
    val userName:String,
    val timeAgo: String,
    val profileImage: Int,
    val postContent: String,
    val postImage: Int? = null
)
